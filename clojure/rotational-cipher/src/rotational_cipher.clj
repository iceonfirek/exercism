(ns rotational-cipher
  (:require [clojure.string :refer [join]])) 

(defn rofun [x n] 
  (cond
    (and (> (int x) 64) (< (int x) 91))
    (char (+ 65 (mod (+ n (- (int x) 65)) 26)))
    (and (> (int x) 96) (< (int x) 123))
    (char (+ 97 (mod (+ n (- (int x) 97)) 26)))
    :else (char x)
    )
  )

(defn rotate [s n]
  (join (map #(rofun % n) (vec (char-array s))))
  ) 

(defn encry [s]
  (rotate s (count s)))
(defn uncry [s]
  (rotate s (- 0 (count s))))
