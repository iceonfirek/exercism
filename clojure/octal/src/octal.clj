(ns octal (:require [clojure.string :refer [split]]))

(defn to-decimal [x]
  (if (re-find #"[^0-7]" x) 0
      (reduce #(+ %2 (* %1 8)) (map read-string (split x #"")))))
