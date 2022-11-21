(ns difference-of-squares)

(defn sum-of-squares [x]
  (/ (* x (inc x) (inc (* 2 x))) 6))

(defn square [x] (* x x))

(defn square-of-sum [x]
  (square (/ (* x (inc x)) 2)))

(defn difference [x]
  (- (square-of-sum x) (sum-of-squares x)))
