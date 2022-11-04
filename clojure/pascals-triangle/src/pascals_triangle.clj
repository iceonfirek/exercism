(ns pascals-triangle)

(defn row [x]
  (cond (= x 1) [1N]
        (= x 2) [1N 1N]
        :else (concat [1N] (map #(reduce + %) (partition 2 1 (row (dec x)))) [1N])))

(def triangle
  (map #(row (inc %)) (range)))

;;

;; (defn next-row [x]
;;   (concat [1N] (map + x (rest x)) [1N]))

;; (def triangle (iterate next-row [1N]))

;; (defn row [x]
;;   (nth triangle (dec x)))

;; 双指针

;; (defn next-row
;;   [row]
;;   (vec
;;    (for [i (range -1 (count row))
;;          :let [j (inc i)]]
;;      (+ (get row i 0)
;;         (get row j 0)))))
;; (def triangle (iterate next-row [1N]))
;; (defn row [num]
;;   (first (drop (dec num) triangle)))

;;

;; (defn next-row [prev-row]
;;   (concat [1N]
;;           (map + prev-row (rest prev-row))
;;           [1N]))
