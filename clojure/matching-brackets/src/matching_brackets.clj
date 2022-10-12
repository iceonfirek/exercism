(ns matching-brackets)

(def brac "()[]{}")
(def brac0 "()][{}")
(def brac1 "([{}])")

(defn valid?
  ([x] (valid? [x 0]))
  ([x i] ;; <- arglist goes here
   (let [j 0]
     (cond 
       (= (nth x i) \() (do (inc j) (valid? x (inc i)))
       (= (nth x i) \)) (do (dec j) (valid? x (inc i)))
       (= i (count (- x 1))) j
       ))
   ))




