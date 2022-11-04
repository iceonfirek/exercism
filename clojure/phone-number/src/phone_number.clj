(ns phone-number (:require [clojure.string :refer [replace]]))

(defn number [s]
  (let [m (apply str (re-seq #"\d" s))]
    (or (last (re-matches #"1?([2-9]\d{2}[2-9]\d{6})" m))
        "0000000000")))

(defn area-code [s]
  (subs (number s) 0 3))

(defn pretty-print [s]
  (replace (number s) #"(\d{3})(\d{3})(\d{4})" "($1) $2-$3"))

;; old version

;; (defn number [s]
;;   (let [m (re-seq #"\d" s)
;;         l (if (and (= (count m) 11) (= "1" (first m))) (rest m) m)]
;;     (cond
;;       (and (= (count l) 10)
;;            (not (= "0" (first l)))
;;            (not (= "1" (first l)))            ;(re-matches #"1?([2-9]\d{2}[2-9]\d{6})"
;;            (not (= "0" (nth l 3)))
;;            (not (= "1" (nth l 3))))
;;       (apply str l)
;;       :else "0000000000")))

;; (defn area-code [s]
;;   (subs (number s) 0 3))

;; (defn pretty-print [s]
;;   (str "(" (subs (number s) 0 3) ") "
;;        (subs (number s) 3 6) "-" (subs (number s) 6 10)))  
                                        ;(s/replace nums #"(\d{3})(\d{3})(\d{4})" "($1) $2-$3")))

;;

;; (defn pretty-print [num-string] 
;;   (apply format "(%s) %s-%s" (rest (re-matches #"(\d{3})(\d{3})(\d{4})" (number num-string)))))
