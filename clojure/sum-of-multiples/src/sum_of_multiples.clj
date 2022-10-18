(ns sum-of-multiples)

(defn sum-of-multiples [y n]
  (->> (for [x y
             z (range 1 n)]
         (cond
           (= 0 (mod z x)) (conj z)))
       (filter some?)
       (into #{})
       (reduce +)))
