(ns space-age)

(defn on-earth [x]
  (float (/ x 31557600)))

(defn on-mercury [x]
  (/ (on-earth x) 0.2408467))

(defn on-venus [x]
  (/ (on-earth x) 0.61519726))

(defn on-mars [x]
  (/ (on-earth x) 1.8808158))

(defn on-jupiter [x]
  (/ (on-earth x) 11.862616))

(defn on-saturn [x]
  (/ (on-earth x) 29.447498))

(defn on-uranus [x]
  (/ (on-earth x) 84.016846))

(defn on-neptune [x]
  (/ (on-earth x) 164.79132))
