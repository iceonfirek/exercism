(ns isogram
  [:require [clojure.string :refer [lower-case replace]]])

(defn isogram? [s]
  (let [l (map int (replace (lower-case s) #" |-" ""))]
    (= (count l) (count (into #{} l)))))


