(ns matching-brackets)

(defn valid?
  ([x] (valid? x 0 0))
  ([x i j]
   (cond
     (< j 0) false
     (and (= i (count x)) (= 0 j)) true
     (and (= i (count x)) (not= 0 j)) false
     (= (nth x i) \() (valid? x (inc i) (inc j))
     (= (nth x i) \)) (valid? x (inc i) (dec j))
     (= (nth x i) \[) (valid? x (inc i) (+ 100 j))
     (= (nth x i) \]) (valid? x (inc i) (- j 100))
     (= (nth x i) \{) (valid? x (inc i) (+ 10000 j))
     (= (nth x i) \}) (valid? x (inc i) (- j 10000))
     )))




