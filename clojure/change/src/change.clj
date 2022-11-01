(ns change)
;;http://programming-puzzler.blogspot.com/2012/11/coin-change-kata-in-racket-and-clojure.html

;;version 2.2 动态规划-迭代 DP-iterate

;;leetwinksi DP
(defn issue [sum coins]
   (when (or (neg? sum) (and (pos? sum) (every? #(< sum %) coins)))
     (throw (IllegalArgumentException. "cannot change")))
   (let [coins (sort coins)
         all-amounts (reduce (fn [cached-amounts amount]
                               (->> coins
                                    (filter #(<= % amount))
                                    (map #(conj (cached-amounts (- amount %) []) %))
                                    (apply min-key count)
                                    (assoc cached-amounts amount)))
                             {} (range 1 (inc sum)))]
     (all-amounts sum)))

;;version 2.1 动态规划-递归 DP-recur

;; (defn issued [x l]
;;   (let [z (mod x (last l))
;;         y (quot x (last l))]
;;     (cond
;;       (and (= 1 (count l)) (not (zero? z))) (throw (IllegalArgumentException. "cannot change"))
;;       (= z 0) (take y (repeat (last l)))
;;       :else (min-key count (issued x (pop l))
;;                      (concat (issued z (pop l)) (take y (repeat (last l))))))))

;; (defn issue [x l]
;;   (cond (= x 0) []
;;         (empty? (filter #(<= % x) l)) (throw (IllegalArgumentException. "cannot change"))
;;         :else (issued x (into [] (sort (filter #(<= % x) l))))))

;; version 1.0 贪心算法 greedy algorithm


;; (defn issue 
;;   [x l]
;;   (let [n (sort (filter #(<= % x) l))]
;;     (cond
;;       (and (empty? n) (not (zero? x)))
;;       (throw (IllegalArgumentException. "cannot change"))
;;       (not (zero? x))
;;       (sort (concat [(last n)] (issue (- x (last n)) l))))))

;; drafts


;; (defn issue1 [amount coins]
;;   (let [coins (sort coins)]
;;     (-> (reduce
;;           (fn [z x]
;;             (->> (map #(conj (z (- x %)) %) coins)
;;                  (filter vector?)
;;                  (reduce (fn ([] nil) ([z x] (min-key count z x))))
;;                  (assoc z x)))
;;           {0 []}
;;           (range 1 (inc amount)))
;;         (get amount)
;;         (or (throw (IllegalArgumentException. "cannot change"))))))

;; (defn compute-matrix [m cost]
;;   (letfn [; Return a function that computes 'cost(k+1,j) + c[i-1,k]'
;;           ; OR just 'c[i-1,k]' if we're on the last row.
;;           (make-min-func [matrix i j]
    ;;         (if (< j (- (count matrix) 1))
    ;;           (fn [k]
    ;;             (+ (cost (+ k 1) j) (get-in matrix [(- i 1) k])))
    ;;           (fn [k]
    ;;             (get-in matrix [(- i 1) k]))))

    ;;       ; Find the minimum cost for the new cost: 'cost(k+1,j)'
    ;;       ; added to the previous entry's cost: 'c[i-1,k]'
    ;;       (min-cost [matrix i j]
    ;;         (let [this-cost (make-min-func matrix i j)
    ;;               rang (range (- i 1) (- j 1))
    ;;               cnt (if (= rang ()) (list (- i 1)) rang)]
    ;;           (apply min (map this-cost cnt))))

    ;;       ; Takes a matrix and indices, returns an updated matrix.
    ;;       (combine [matrix indices]
    ;;         (let [i (first indices)
    ;;               j (nth indices 1)
    ;;               opt (min-cost matrix i j)]
    ;;           (assoc-in matrix [i j] opt)))]

    ;; (reduce combine m
    ;;         (for [i (range 1 (count m)) j (range i (count m))] [i j]))))




