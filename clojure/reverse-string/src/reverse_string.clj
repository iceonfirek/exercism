(ns reverse-string)

;; (defn reverse-string [s] ;; <- arglist goes here
;;   (if (> (count s) 0) (str (reverse-string (rest s)) (first s))
;;       ""))

(defn reverse-string [s]
  (reduce #(str %2 %1) "" s))
