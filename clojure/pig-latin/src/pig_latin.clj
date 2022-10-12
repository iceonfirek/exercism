(ns pig-latin
  (:require [clojure.string :refer [join split]]))

(def vowel ["a" "e" "i" "o" "u"])
(def vowel2 ["yt" "xr"])
(def cons1 ["ch" "qu" "th" "rh"])
(def cons2 ["squ" "thr" "sch" ])

(defn trans
  ([w] (trans w 0))
  ([w i] (cond
           (and (= (count w) 2) (= \y (second w))) (str (subs w 1) (subs w 0 1) "ay")
           (some #(= (subs w 0 2) %) vowel2) (str w "ay")
           (some #(= (subs w 0 3) %) cons2) (str (subs w 3) (subs w 0 3) "ay")
           (some #(= (subs w 0 2) %) cons1) (str (subs w 2) (subs w 0 2) "ay")
           (some #(= (str (nth w i)) %) vowel) (str (subs w i) (subs w 0 i) "ay")
          :else (trans w (inc i)))))

(defn translate [s]
  (let [w (split s #"\s")]
    (join " "
          (keep #(trans %) w))
    )
  )



