(ns word-count
  [:require [clojure.string :refer [lower-case]]])

(defn word-count [s]
  (frequencies (re-seq #"\w+" (lower-case s))))
