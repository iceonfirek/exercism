(ns acronym
  (:require [clojure.string :refer [split replace upper-case]]))

(defn acronym
  "Converts phrase to its acronym."
  [phrase]
  (->> (split (replace phrase #"([A-Z]+)" " $1") #"\ |-")
       (map first)
       (apply str)
       upper-case))
