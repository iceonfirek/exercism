(ns pov)

(defn path-to [x s se]
  (if (= x (first s)) (conj se x)
      (->> (map #(path-to x % (conj se (first s))) (rest s))
           (remove empty?))))

(defn get_value [x]
  (cond (= x []) nil
        (vector? x) x
        (vector? (first x)) (first x)
        :else (get_value (first x))))

(defn get_parents [x s]
  (->> (for [a (rest s)]
         (if
          (= x (first a)) (conj a (into [] (remove #(= % a) s)))))
       (remove nil?)
       first))

(defn of [x s]
  (let [n (get_value (path-to x s []))
        m (rest n)]
    (if (= n nil) nil
        (loop [i 0
               s s]
          (if (= i (count m)) s
              (recur (inc i) (get_parents (nth m i) s)))))))

(defn remove_vec [x y]
  (loop [a x
         b y]
    (if (= a []) b
        (recur (rest a) (remove #(= (first a) %) b)))))

(defn path-from-to [x y s]
  (let [m (reverse (get_value (path-to x s [])))
        n (get_value (path-to y s []))]
    (if (or (nil? m) (nil? n)) nil (concat m (remove_vec m n)))))



;; tree-seq solution
;; (ns pov)
;; (defn filter-tree [x tree]
;;   (->> (tree-seq next rest tree)
;;        (filter #(some #{x} (flatten %)))
;;        not-empty))
;; (defn of [x tree]
;;   (some->> (filter-tree x tree)
;;            (reduce #(conj %2 (vec (remove #{%2} %1))))))
;; (defn path-from-to [f t tree]
;;   (some->> (of f tree)
;;            (filter-tree t)
;;            (mapv first)))
