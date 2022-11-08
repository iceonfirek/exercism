(ns proverb (:require [clojure.string :refer [join]]))

(defn recite [x]
  (if (= (count x) 0) ""
      (->> (for [n (range (dec (count x)))]
             (format "For want of a %s the %s was lost." (nth x n) (nth x (inc n))))
           (into [])
           (#(conj % (format "And all for the want of a %s." (first x))))
           (join "\n"))))
