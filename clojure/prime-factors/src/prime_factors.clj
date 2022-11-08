(ns prime-factors)

(defn of [x]
  (loop [m x
         n 2
         l []]
    (cond
      (= m 1) l
   ;;   (= 0 (- m n)) (concat l [n])
      (zero? (mod m n)) (recur (quot m n) n (concat l [n]))
      :else (recur m (inc n) l))))

;; recur approach (sof bignums)

;; (defn of ([x] (if (= 1 x) [] (of x 2)))
;;   ([x y]
;;    (cond
;;      (= 0 (- x y)) (concat [y])
;;      (zero? (mod x y)) (concat [y] (of (quot x y) y))
;;      :else (of x (inc y)))))

