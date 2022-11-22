(ns sieve)

(defn sieve_list [[head & tail :as coll]]
  (if (= [] coll) []
      (concat [head] (sieve_list (remove #(= 0 (mod % head)) tail)))))
(defn sieve [x]
  (sieve_list (range 2 (inc x))))
