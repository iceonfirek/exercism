(ns rna-transcription
  (:require [clojure.string :refer [split join]]) )

(defn to-rna [dna]
  (join (for [x (split dna #"")]
          (cond
            (= x "G") "C"
            (= x "C") "G"
            (= x "T") "A"
            (= x "A") "U"
            :else (throw (AssertionError.))
            ))))
