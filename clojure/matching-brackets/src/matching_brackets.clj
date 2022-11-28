(ns matching-brackets (:require [clojure.string :as str]))

(defn pairs? [a b]
  (cond (and (= a \() (= b \))) true
        (and (= a \[) (= b \])) true
        (and (= a \{) (= b \})) true
        :else false))

(defn valid? [s]
  (if (= s "") true
      (let [y (seq (str/replace s #"[^\(\)\[\]\{\}]" ""))]
        (loop [x y m []]
          (cond (and (= x []) (not (= m []))) false
                (and (= x []) (= m [])) true
                (pairs? (last m) (first x)) (recur (rest x) (pop m))
                :else (recur (rest x) (conj m (first x))))))))

;; goderich's solution on exercism

;; TIL that a `reduce` can be short-circuited
;; using `reduced`. Very useful.
;; Non-matching brackets return a bogus
;; non-empty list, so that both this outcome
;; as well as unmatched dangling brackets
;; can be checked against using `empty?`.
(defn- matcher [[fst & rst :as acc] c]
  (case c
    (\{ \[ \() (cons c acc)
    \} (if (= \{ fst) rst (reduced [false]))
    \] (if (= \[ fst) rst (reduced [false]))
    \) (if (= \( fst) rst (reduced [false]))
    acc))
(defn valid?_ [s]
  (empty? (reduce matcher [] s)))

;; (defn valid?
;;   ([x] (valid? x 0 0))
;;   ([s i j]
;;    (let [x (str/replace s #"[^\(\)\[\]\{\}]" "")]
;;      (cond
;;        (< j 0) false
;;        (and (= i (count x)) (= 0 j)) true
;;        (and (= i (count x)) (not= 0 j)) false
;;        (= (nth x i) \() (valid? x (inc i) (inc j))
;;        (= (nth x i) \)) (valid? x (inc i) (dec j))
;;        (= (nth x i) \[) (valid? x (inc i) (+ 100 j))
;;        (= (nth x i) \]) (valid? x (inc i) (- j 100))
;;        (= (nth x i) \{) (valid? x (inc i) (+ 10000 j))
;;        (= (nth x i) \}) (valid? x (inc i) (- j 10000))
;;        ))))



