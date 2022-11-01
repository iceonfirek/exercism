(ns binary-search)

(defn middle [s] ;; <- arglist goes here
  (quot (count s) 2))

(defn search-for
  ([x s] (search-for x s (middle s) (quot (middle s) 2))) ;; <- arglist goes here
  ([x s n m]
   (cond (> x (last s)) (throw (Exception. "not found"))
         (= x (nth s n))  n
         (< x (nth s n)) (search-for x s (- n m) (if (even? m) (quot m 2) (quot (+ 1 m) 2)))
         (> x (nth s n)) (search-for x s (+ n m) (if (even? m) (quot m 2) (quot (+ 1 m) 2))))))


