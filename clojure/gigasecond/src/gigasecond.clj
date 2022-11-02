(ns gigasecond)

(defn from-r [y m d]
  (cond
    (= m 13) (from-r (inc y) 1 d)
    (and (zero? (mod y 4)) (= m 2) (> d 29)) (from-r y (inc m) (- d 29))
    (and (= m 2) (> d 28)) (from-r y (inc m) (- d 28))
    (and (some #(= m %) [1 3 5 7 8 10 12]) (> d 31)) (from-r y (inc m) (- d 31))
    (and (some #(= m %) [4 6 9 11]) (> d 30)) (from-r y (inc m) (- d 30))
    :else (vector y m d)))

(defn from [y m d]
  (from-r y m (+ 11574 d)))
