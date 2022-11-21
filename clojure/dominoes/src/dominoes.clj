(ns dominoes)

(defn any [[f s] x]
  (or (= f x) (= x s)))
(defn resd [[f s] x]
  (if (= s x) [s f]
      [f s]))
(defn reseq [x c l]
  (loop [n 0
         m []]
    (cond
      (= n (dec l))
      m
      (any (nth c n) x)
      (recur (inc n) (concat m [(concat (take n c) (take-last (- (count c) (inc n)) c) [(resd (nth c n) x)])]))
      :else (recur (inc n) m))))
(defn find-any [x l]
  (remove nil? (for [[m n] l]
                 (cond (or (= x n) (= x m)) [m n]
                       :else nil))))

(defn can-chain? ([x] (cond (= x []) []
                            (and (= 1 (count x)) (not= (first (first x)) (last (first x)))) false
                            :else (can-chain? x (count x))))
  ([x l]
   (let [m (last (last x))]
     (cond
       (= l 1) x
       (= [] (find-any m (take (dec l) x))) false
       :else (or (can-chain? (first (reseq m x l)) (dec l))
                 (can-chain? (second (reseq m x l)) (dec l)))))))

;; (defn other [[f s] x]
;;   (if (= f x) [s f] [f s]))
;; (defn anyp [[f s] [l m]]
;;   (or (= f l) (= f m) (= s l) (= s m)))
;; (defn anyo [[f s] [l m]]
;;   (cond
;;     (= f l) [[s f] [l m]]
;;     (= f m) [[s f] [m l]]
;;     (= s l) [[f s] [l m]]
;;     (= s m) [[f s] [m l]]))
;; (defn reon [x s]
;;   (let [n (count (filter #(= % x) s))]
;;     (if (> n 1)
;;       (concat (remove #(= % x) s) (repeat (dec n) x))
;;       (remove #(= % x) s))))
;; (defn select-l [n c]
;;   (concat (take (dec n) c) (take-last (- (count c) n) c) [(nth c n)]))

;; (defn max-count [x y]
;;   (if (> (count x) (count y)) x y))
;; (defn nonil [x y]
;;   (if (some #(nil? %) x) y x))


(defn can-chain1? [xs & [[[a _] & _ :as rs]]]
  (let [c  (count xs)
        ts (take c (partition c 1 (cycle xs)))
        f  #(can-chain1? %1 (conj rs %2))]
    (if (empty? xs)
      (= a (last (last rs)))
      (some identity (map (fn [[[x y] & r]]
                            (cond (nil? a) (or (f r [x y]) (f r [y x]))
                                  (= y a)  (f r [x y])
                                  (= x a)  (f r [y x]))) ts)))))
