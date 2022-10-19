(ns sum-of-multiples)

(defn sum-of-multiples [y n]
  (->> (for [x y
             z (range 1 n)]
         (cond
           (= 0 (mod z x)) (conj z)))
       (filter some?)
       (into #{})
       (reduce +)))

;; ########################
;; (defn sum-of-n [x n]
;;   (cond
;;     (every? #(= 0 (quot n %)) x) 0
;;     (some #(= 0 (mod n %)) x) (+ n (sum-of-n x (- n 1)))
;;     :else (sum-of-n x (- n 1))))

;; (defn sum-of-multiples [x n]
;;   (sum-of-n x (- n 1)))

;; #########################
;; (defn sum-of-1 [y n]
;;   (reduce + (map #(quot (* % (quot (- n 1) %) (+ (quot (- n 1) %) 1)) 2) y)))
