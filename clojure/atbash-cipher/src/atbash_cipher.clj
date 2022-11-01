(ns atbash-cipher
  [:require [clojure.string :refer [lower-case join replace]]])

(defn transw [x]
  (if (and (> (int x) 96) (< (int x) 123))
    (char (- 122 (- (int x) 97)))
    x))

(defn encode [s]
  (->> (replace (lower-case s) #"[^\d\w]+" "")
       (map transw)
       (partition-all 5)
       (map join)
       (join " ")))


;; (def ciset (split "abcdefghijklmnopqrstuvwxyz0123456789" #""))
;; (def plset (split "zyxwvutsrqponmlkjihgfedcba0123456789" #""))
;; (def cpset
;;   (into {} (for [i (range 36)]
;;              (vector (nth ciset i) (nth plset i)))))

;; (defn encode2 [s]
;;   (->>  (split (lower-case s) #"")
;;         (filter #(re-matches #"\w" %))
;;         (map #(cpset %))
;;         (partition-all 5)
;;         (map join)
;;         (join " ")))
