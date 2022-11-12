(ns allergies)

(def aset {1 :eggs 2 :peanuts 4 :shellfish 8 :strawberries 16 :tomatoes 32 :chocolate 64 :pollen 128 :cats})

(defn allergies [n]
  (->> (loop [n n m 1 res ()]
         (let [r (rem n 2)
               q (quot n 2)
               res (conj res (when (= 1 r) (aset m)))]
           (if (zero? q)
             res
             (recur q (* 2 m) res))))
       (remove nil?)
       reverse))

(defn allergic-to? [n item]
  (contains? (into #{} (allergies n)) item))

