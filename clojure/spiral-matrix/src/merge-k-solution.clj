;;Ref: https://stackoverflow.com/questions/62806958/fast-sorting-algorithm-for-multiple-ordered-arrays
(require ['clojure.core.reducers :as 'reducers])

(defn mapmap [f m]
  (reduce #(update %1 %2 f) m (keys m)))

(defn unfold [f s]
  (->> s
       (list nil)
       (iterate (comp f second))
       rest
       (take-while some?)
       (map first)))

(defn foldh [f s]
  ((fn rec [v]
     (f (if (> (count v) 2)
          (let [h (quot (count v) 2)]
            (map rec [(subvec v 0 h) (subvec v h)]))
          v)))
   (vec s)))

(defn fold2 [f s]
  (loop [s s]
    (if (nnext s)
      (recur (map f (partition-all 2 s)))
      (f s))))

(def merge-sorted
  (merge
    ;direct lazy algorithms
    (mapmap
      (fn [[prepare choose insert]]
        (fn [s]
          (->> s
               (filter seq)
               prepare
               (unfold
                 (fn [s]
                   (if (seq s)
                     (let [[[xf & xn] s] (choose s)]
                       [xf (if xn (insert s xn) s)])))))))
      {:min
       [identity
        (fn [s]
          (reduce
            (fn [[x s] y]
              (if (< (first x) (first y))
                [x (cons y s)]
                [y (cons x s)]))
            [(first s) ()]
            (rest s)))
        conj]
       :sort
       [(partial sort-by first)
        (juxt first rest)
        (fn [s [xf :as x]]
          (let [[a b] (loop [a () b (seq s)]
                        (if-let [[bf & bn] b]
                          (if (< (first bf) xf)
                            (recur (cons bf a) bn)
                            [a b])
                          [a b]))]
            (into (cons x b) a)))]
       :lsort
       [(partial sort-by first)
        (juxt first rest)
        (fn [s [xf :as x]]
          ((fn rec [s]
             (lazy-seq
               (if-let [[sf] (seq s)]
                 (if (< (first sf) xf)
                   (cons sf (rec (rest s)))
                   (cons x s))
                 (list x))))
           s))]
       :heap
       [(fn [s]
          (let [h (java.util.PriorityQueue.
                    (count s)
                    #(< (first %1) (first %2)))]
            (run! #(.add h %) s)
            h))
        (fn [h] [(.poll h) h])
        (fn [h x] (.add h x) h)]})
    ;folding lazy algorithms
    (mapmap
      (letfn [(merge2 [s]
                (lazy-seq
                  (if-let [[x & s] (seq (filter seq s))]
                    (if-let [[y] s]
                      ((fn rec [x y]
                         (lazy-seq
                           (let [[[xf & xn] y]
                                 (if (< (first x) (first y))
                                   [x y]
                                   [y x])]
                             (cons xf (if xn (rec xn y) y)))))
                       x y)
                      x))))]
        (fn [fold] (partial fold merge2)))
      {:foldl #(reduce (comp %1 list) %2)
       :foldh foldh
       :fold2 fold2})
    ;folding eager algorithms
    (mapmap
      (letfn [(merge2 [s]
                (if-let [[x & s] (seq (filter seq s))]
                  (if-let [[y] s]
                    (loop [x x y y acc ()]
                      (let [[[xf & xn] y]
                            (if (< (first x) (first y))
                              [x y]
                              [y x])
                            acc (conj acc xf)]
                        (if xn
                          (recur xn y acc)
                          (into y acc))))
                    x)
                  ()))]
        (fn [fold] (partial fold merge2)))
      {:efoldp #(reducers/fold 2 (comp %1 list) (comp %1 list) (vec %2))
       :efoldh foldh
       :efold2 fold2})))

(defn gen-inp [m n]
  (->> 0
       (repeat m)
       (map
         (comp
           doall
           (partial take n)
           rest
           (partial iterate #(+ % (rand-int 100)))))
       doall))

(defn test-merge-sorted [m n & algs]
   (->> (or algs (sort (keys merge-sorted)))
        (map (juxt name merge-sorted))
        (run!
          (let [inp (gen-inp m n)]
            (fn [[id alg]]
              (println id)
              ;(java.lang.System/gc)
              (try
                (time (doall (alg inp)))
                (catch java.lang.StackOverflowError _
                  (prn "Stack overflow"))))))))
