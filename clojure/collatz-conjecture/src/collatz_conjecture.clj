(ns collatz-conjecture)

(defn collatz
  ([num] (collatz num 0))
  ([num i]
   (if (= 1 num) i
       (if (even? num)
         (collatz (quot num 2) (inc i))
         (collatz (+ 1 (* 3 num)) (inc i))))))
