(ns roman-numerals
  (:require [clojure.string :refer [join]]))

(defn numerals [x]
  (cond
    (>= x 1000) (str (join (take (quot x 1000) (repeat \M))) (numerals (rem x 1000)))
    (>= x 900) (str "CM" (numerals (- x 900)))
    (>= x 500) (str (join (take (quot x 500) (repeat \D))) (numerals (rem x 500)))
    (>= x 400) (str "CD" (numerals (- x 400)))
    (>= x 100) (str (join (take (quot x 100) (repeat \C))) (numerals (rem x 100)))
    (>= x 90) (str "XC" (numerals (- x 90)))
    (>= x 50) (str (join (take (quot x 50) (repeat \L))) (numerals (rem x 50)))
    (>= x 40) (str "XL" (numerals (- x 40)))
    (>= x 10) (str (join (take (quot x 10) (repeat\X))) (numerals (rem x 10)))
    (>= x 9) (str "IX")
    (>= x 5) (str (join (take (quot x 5) (repeat \V))) (numerals (rem x 5)))
    (>= x 4) (str "IV")
    :else (join (repeat x \I))
    ))

;; (defn fa [a b c x]
;;   (cond
;;     (>= x 1000) (str (join (take (quot x 1000) (repeat c))) (fa a b c (rem x 1000)))
;;     (>= x 900) (str a c (fa a b c (- x 900)))
;;     (>= x 500) (str (join (take (quot x 500) (repeat b))) (fa a b c (rem x 500)))
;;     (>= x 400) (str a b (fa a b c (- x 400)))
;;     (>= x 100) (join (repeat (quot x 100) a))
;;     )
;;   )

