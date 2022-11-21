(ns crypto-square (:require [clojure.string :refer [lower-case replace join]]))

(defn normalize-plaintext [x]
  (replace (lower-case x) #"[^a-z0-9]" ""))

(defn square-size [x]
  (let [s (normalize-plaintext x)]
    (loop [c 2]
      (let [n (count s)
            r (inc (quot n c))]
        (if (and (>= c r) (<= (- c r) 1))
          r
          (recur (inc c)))))))

(defn plaintext-segments [x]
  (let [s (normalize-plaintext x)
        c (inc (quot (count s) (square-size s)))]
    (->> (partition-all c s)
         (map #(apply str %)))))

(defn normalize-ciphertext [x]
  (let [s (normalize-plaintext x)
        c (inc (quot (count s) (square-size s)))
        z (mod (count s) c)]
    (->> (str s (apply str (repeat (- c (if (= 0 z) c z)) " ")))
         (partition-all c c)
         (apply map str)
         (join " "))))

(defn ciphertext [x]
  (-> (normalize-ciphertext x)
      (replace #" " "")))
