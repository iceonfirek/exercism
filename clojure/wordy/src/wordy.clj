(ns wordy (:require [clojure.string :refer [split replace]]))

(def wordy_map {"plus" + "minus" - "multiplied" * "divided" /})

(defn evaluate [s]
  (let [x (split (replace s #"\?|What |is |by " "") #" ")]
    (if (nil? (some wordy_map x)) (throw (IllegalArgumentException.))
        (reduce (fn [a [c d]] ((wordy_map c) a (read-string d)))
                (read-string (first x)) (partition 2 2 (rest x))))))


