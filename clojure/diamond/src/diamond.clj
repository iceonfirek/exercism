(ns diamond (:require [clojure.string :refer [join]]))

(defn diamond [x]
  (let [n (- (int x) 64)
        total (- (* 2 n) 3)]
    (for [i (concat (range n) (range (- n 2) -1 -1))]
      (let [sn (- n (inc i))
            sc (str (char (int (+ 65 i))))]
        (if (= i 0)
          (str (join (repeat sn " ")) sc (join (repeat sn " ")))
          (str (join (repeat sn " ")) sc (join (repeat (- total (* 2 sn)) " ")) sc (join (repeat sn " "))))))))
