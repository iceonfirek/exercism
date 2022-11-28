(ns queen-attack (:require [clojure.string :as str]))

(defn board-string [{[ax ay] :w [bx by] :b}]
  (->> (for [x (range 8)]
         (for [y (range 8)]
           (cond (= [ax ay] [x y]) \W
                 (= [bx by] [x y]) \B
                 :else \_)))
       (map #(str/join " " %))
       (map #(str % "\n"))
       str/join))

(defn can-attack [{[ax ay] :w [bx by] :b}]
  (or (= ax bx)
      (= ay by)
      (= (Math/abs (- ax bx)) (Math/abs (- ay by)))))

;;previous version
;; (def board [["_ " "_ " "_ " "_ " "_ " "_ " "_ " "_\n"]
;;             ["_ " "_ " "_ " "_ " "_ " "_ " "_ " "_\n"]
;;             ["_ " "_ " "_ " "_ " "_ " "_ " "_ " "_\n"]
;;             ["_ " "_ " "_ " "_ " "_ " "_ " "_ " "_\n"]
;;             ["_ " "_ " "_ " "_ " "_ " "_ " "_ " "_\n"]
;;             ["_ " "_ " "_ " "_ " "_ " "_ " "_ " "_\n"]
;;             ["_ " "_ " "_ " "_ " "_ " "_ " "_ " "_\n"]
;;             ["_ " "_ " "_ " "_ " "_ " "_ " "_ " "_\n"]])
;; (defn board-string [l]
;;   (if (= l {})
;;     (reduce #(str %1 (apply str %2)) "" board)
;;     (let [x (:w l)
;;           y (:b l)
;;           s (update-in board x #(str/replace % "_" "W"))]
;;       (->> (update-in s y #(str/replace % "_" "B"))
;;            (reduce #(str %1 (apply str %2)) "")))))

;; (def coll (range 8))
;; (defn match_arrays [coll]
;;   (apply concat
;;          (for [i (range 2 9)]
;;            [(map vector (take i coll) (take-last i coll))
;;             (map vector (take-last i coll) (take i coll))
;;             (map vector (take i coll) (take-last i (reverse coll)))
;;             (map vector (take-last i coll) (take i (reverse coll)))])))

;; (defn can-attack [l]
;;   (let [x (:w l)
;;         y (:b l)]
;;     (loop [z (match_arrays coll)]
;;       (cond
;;         (= [] z) false
;;         (or (= (first x) (first y)) (= (second x) (second y))) true
;;         (and (some #(= x %) (first z)) (some #(= y %) (first z))) true
;;         :else (recur (rest z))))))

