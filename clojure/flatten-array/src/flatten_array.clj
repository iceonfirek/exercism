(ns flatten-array)

(defn flatten [arr]
  (reduce #(if (seqable? %2)
             (into [] (concat %1 (flatten %2)))
             (conj %1 %2)) [] arr))

;; reduce solution 1


;; (defn flatten [arr]
;;   (reduce #(concat %1 (cond (coll? %2) (flatten %2)
;;                             (nil? %2) []
;;                             :else [%2])) [] arr))
;; recur solution

;; (defn flatten [arr]
;;   (->> (let [n (first arr)
;;              m (rest arr)]
;;          (cond (= [] arr) []
;;                (nil? n) (flatten m)
;;                (vector? n) (concat (flatten n) (flatten m))
;;                :else (cons n (flatten m))))))
