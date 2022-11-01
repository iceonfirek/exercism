
(defn fib ([n] (fib 0 1 n))
  ([a b n]
   (if (= (dec n) 0) a
       (fib b (+ a b) (- n 1)))))

(defn fib1 [n]
  (cond (= n 1) 0
        (= n 2) 1
        :else (+ (fib1 (- n 1)) (fib1 (- n 2)))))

(defn fib2 [n]
  (nth (map first (iterate (fn [[x y]]
                             [y (+ x y)])
                           [0 1])) (dec n)))
(defn top-down [n]
  (with-redefs [fib1 (memoize fib1)]
    (fib1 n))
  )

