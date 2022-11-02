(ns grains)

(defn square [x]
;;  (reduce *' (repeat (dec x) 2))
  (.pow (biginteger 2) (dec x))
  )

(defn total [] (dec (square 65)))
