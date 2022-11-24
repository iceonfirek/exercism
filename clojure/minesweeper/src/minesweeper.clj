(ns minesweeper (:require [clojure.string :as str]))

(defn num_inc [x]
  (if (number? x) (inc x) x))
(defn num_plus [x y]
  (if (number? x) (+ x y) x))

(defn map_str [s]
  (str/replace (reduce #(str %1 %2) s) "0" " "))

(defn rep [s]
  (into [] (-> (str/replace s #" " "0")
               (str/split #"")
               (#(map (fn [x] (if (= x "0") 0 x)) %)))))

(defn map_plus [m n]
  (for [i (range (count m))]
    (map num_plus (nth m i) (nth n i))))

(defn update-n [s m n]
  (let [x (for [c (if (= m 0)
                    [0 1]
                    [(dec m) m (inc m)])
                r (if (= n 0)
                    [0 1]
                    [(dec n) n (inc n)])]
            (update-in s [c r] num_inc))]
    (reduce #(map_plus %1 %2) x)))

(defn draw [s]
  (if (not (str/includes? s "*")) s
      (let [x (into [] (map rep (str/split s #"\n")))]
        (->> (for [m (range (count (first x)))
                   n (range (count x))]
               (if (= "*" (nth (nth x n) m)) (update-n x n m)
                   nil))
             (remove nil?)
             (reduce #(map_plus %1 %2))
             (map map_str)
             (str/join "\n")))))



