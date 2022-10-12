(ns anagram
  (:require [clojure.string :as str]))

(defn- is-equal? [x y]
  (let [a (str/lower-case x) b (str/lower-case y)]
    (if (= a b) false
        (= (sort (str/split a #"")) (sort (str/split b #""))))))


(defn anagrams-for [word list]
  (filter #(is-equal? % word) list))
