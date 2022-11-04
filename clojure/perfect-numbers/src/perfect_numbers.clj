(ns perfect-numbers)

(defn classify [x]
  (let [y (reduce #(+ %1 (if (zero? (mod x %2)) %2 0)) (range 1 (inc (quot x 2))))]
    (cond (= x y) :perfect
          (< x y) :abundant
          (> x y) :deficient)))
