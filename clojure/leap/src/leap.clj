(ns leap)

(defn leap-year? [year]
  (cond 
    (and (zero? (mod year 4)) (zero? (mod year 400))) true
    (and (zero? (mod year 4)) (zero? (mod year 100))) false
    (zero? (mod year 4)) true
    :else false)
)

;; (defn leap-year? [year]
;;   (and (= 0 (mod year 4))
;;        (or (not= 0 (mod year 100))
;;            (= 0 (mod year 400))))
;;   )

;;

;; (defn divisible-by? [x y] (zero? (rem y x)))
;; (defn leap-year? [year]
;;   (condp divisible-by? year
;;     400 true
;;     100 false
;;     4 true
;;     false))
