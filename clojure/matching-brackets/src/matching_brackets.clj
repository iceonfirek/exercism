(ns matching-brackets)

(def brac "()[]{}")
(def brac0 "()][{}")
(def brac1 "([{}])")

(defn valid?
  ([x] (valid? x 0 0))
  ([x i j]
   (cond
     (< j 0) false
     (and  (= i (count x)) (= 0 j)) true
     (and (= i (count x)) (not= 0 j)) false
     (= (nth x i) \() (valid? x (inc i) (inc j))
     (= (nth x i) \)) (valid? x (inc i) (dec j))
     ) ))




