(ns run-length-encoding (:require [clojure.string :refer [join]])) 

(defn reg [w]
  (let [[a b] w]
    (if (= b nil) a
        (str (count a) b))))

(defn run-length-encode [s]
  (join (map #(reg %) (re-seq #"([a-zA-Z]|\s)\1+|[a-zA-Z]|\s" s))))

(defn reg2 [w]
  (let [[_ b c] w]
    (if (= b "") c
        (join (repeat (read-string b) c)))))

(defn run-length-decode [s]
  (join (map #(reg2 %) (re-seq #"(\d*)(\D)" s))))

;; (defn reg [w]
;;   (if (= 1 (count (first w))) (first w)
;;       (str (str (count (first w))) (first (first w)))))

;; (defn reg2 [w]
;;   (if (= 1 (count w)) w
;;       (join (take (read-string (re-find #"[0-9]+" w)) (repeat (str (last w)))))))

;; (defn run-length-decode [s]
;;   (join (map #(reg2 %) (re-seq #"[0-9]*[a-zA-Z\s]" s))))


