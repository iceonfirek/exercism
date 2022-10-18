(ns scrabble-score (:require [clojure.string :refer [split lower-case]]))

(def score-letter-map
  {["a" "e" "i" "o" "u" "l" "n" "r" "s" "t"] 1,
   ["d" "g"] 2,
   ["b" "c" "m" "p"] 3,
   ["f" "h" "v" "w" "y"] 4,
   ["k"] 5,
   ["j" "x"] 8,
   ["q" "z"] 10})

(defn score-letter [x]
  (def score-letter-mapx 
    (into {}
          (for [[a b] score-letter-map
                c a]
            [c b])))
  (score-letter-mapx (lower-case x)))

(defn score-word [s]
  (reduce + (map score-letter (split (lower-case s) #""))))



