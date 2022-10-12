(ns all-your-base)

(defn exp [x y]
  (if (> y 0)
    (* x (exp x (- y 1))) 1))

(defn int [x]
   (map read-string (map str (str x))))

(defn convert [a b] ;; <- arglist goes here
  (fn [x]
    (if (< x (+ 1 (count (int a))))
      (let [x 0]
        (+ (* (nth (reverse (int a)) x) (exp b x)) (convert (Integer/parseInt (clojure.string/join (pop (int a)))) b))
    ;; (> (count (int a)) 1)
    ;; (+ (* (nth (reverse (int a)) (- (count (int a)) 1)) (exp b (- (count (int a)) 1))) (convert (Integer/parseInt (clojure.string/join (rest (int a)))) b))
    ;; (= (count (int a)) 1)
    ;; (+ (* (nth (reverse (int a)) (- (count (int a)) 1)) (exp b (- (count (int a)) 1))) (convert (Integer/parseInt (clojure.string/join (last (int a)))) b))
    (+ 1 x)))))
