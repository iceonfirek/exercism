(ns kindergarten-garden [:require [clojure.string :refer [lower-case split]]])

(def p-set {\V :violets \R :radishes \G :grass \C :clover})
(def full-names ["Alice" "Bob" "Charlie" "David" "Eve" "Fred" "Ginny" "Harriet" "Ileana" "Joseph" "Kincaid" "Larry"])

(defn garden ([plots] (garden plots full-names))
  ([plots names]
   (let [plot (split plots #"\n")
         sorted-names (sort names)]
     (reduce #(merge-with concat %1 %2)
             (for [x plot
                   i (range (quot (count x) 2))
                   n (range (* 2 i) (* 2 (inc i)))]
               {(keyword (lower-case (nth sorted-names i)))
                [(p-set (nth x n))]})))))


