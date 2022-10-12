(ns nth-prime)

;; (defn prime
;;   ([x]
;;    (prime x 2))
;;   ([x n]
;;    (cond (> (* n n) x) x
;;          (= 0 (rem x n)) n
;;          :else (prime x (+ 1 n))))
;;   )

;; (defn prime? [x]
;;   (if (> x 1)
;;     (= x (prime x)) false))

(defn prime?
  [x]
  (not (some #(zero? (mod x %)) (range 2 (inc (int (Math/sqrt x)))))))

;; (defn nth-prime 
;;   "Returns the prime number in the nth position."
;;   ([n]
;;    (if (= n 0) (throw (IllegalArgumentException. "hello"))
;;        (nth-prime n 2)))
;;   ([n x]
;;    (if (= n 1) x
;;        (if (prime? x) ;;(count (filter true? (map #(prime? %) (range x)))
;;          (nth-prime (- n 1) (inc x))
;;          (nth-prime n (inc x)))))
;;    )
  
(defn nth-prime [n]
  (when (< n 1)
    (throw (IllegalArgumentException.)))
  (nth (cons 2 (filter prime? (iterate (partial + 2) 3))) (dec n)))


