(ns prime-factors)

(defn of [x]
  (reduce #(if (zero? %1 %2) ) [x] (range 2 x))
  )

;; recur approach (sof bignums)

;; (defn of ([x] (if (= 1 x) [] (of x 2)))
;;   ([x y]
;;    (cond
;;      (= 0 (- x y)) (concat [y])
;;      (zero? (mod x y)) (concat [y] (of (quot x y) y))
;;      :else (of x (inc y)))))

