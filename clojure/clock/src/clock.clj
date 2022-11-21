(ns clock)

(defn tr [x]
  (if (< x 10) (str "0" x) x))

(defn clock [h m]
  (if (>= m 0)
    [(mod (+ h (quot m 60)) 24) (mod m 60)]
    [(mod (dec (mod (+ h (quot m 60)) 24)) 24) (mod m 60)]))

(defn add-time [[h m] time]
  (clock h (+ time m)))

(defn clock->string [[h m]]
  (format "%s:%s" (tr h) (tr m)))
