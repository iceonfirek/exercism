(ns trinary)

(defn to-decimal [s]
  (if (not-every? #{\1 \2 \0} s) 0
    (->> (re-seq #"." s)
         (map read-string)
         (reduce #(+ (* 3 %) %2)))))

;; first version

;; (defn to-decimal [s]
;;   (->> (re-seq #"." s)
;;        (map read-string)
;;        (#(if (every? #{0 1 2} %) % [0]))
;;        (reduce #(+ (* 3 %1) %2))))
