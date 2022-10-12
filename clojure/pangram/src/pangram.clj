(ns pangram
  (:require [clojure.string :refer [lower-case]]))

(defn pangram? [s]
  (= 26 (count (distinct (re-seq #"[a-z]" (lower-case s))))))
