(ns hexadecimal)

(def hexm (zipmap "0123456789abcdef" (range)))

(defn hex-to-int [s]
  (if (every? hexm s)
    (->> (map hexm (seq s))
         (reduce #(+ (* 16 %1) %2)))
    0))

;;reference
;;clojure/all-your-base/src/all_your_base.clj

;; (def hexm (zipmap "0123456789abcdef" (range 16)))

;; (defn hex-to-int [s]
;;   (if (every? hexm s)
;;     (->> (seq s)
;;          (map hexm)
;;          reverse
;;          (map * (map #(.pow (biginteger 16) %) (range (count s))))
;;          (reduce +))
;;     0))

;; ;;

;; (def hexm {"0" 0 "1" 1 "2" 2 "3" 3 "4" 4 "5" 5 "6" 6 "7" 7 "8" 8 "9" 9 "a" 10 "b" 11 "c" 12 "d" 13 "e" 14 "f" 15 "g" 16})

;; (defn hex-to-int [s]
;;   (let [l (map #(hexm %) (split s #""))]
;;     (if (every? identity l)
;;       (reduce + (map * (map #(.pow (biginteger 16) %) (range (count s))) (reverse l)))
;;       0)))


