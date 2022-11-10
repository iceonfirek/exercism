(ns say) ;; reference from vl0ft's solution

(def nset {0 "zero", 70 "seventy", 7 "seven", 20 "twenty", 60 "sixty", 1 "one", 4 "four", 1000000 "million", 15 "fifteen", 50 "fifty", 40 "forty", 1000 "thousand", 13 "thirteen", 90 "ninety", 100 "hundred", 6 "six", 1000000000 "billion", 17 "seventeen", 3 "three", 12 "twelve", 2 "two", 19 "nineteen", 11 "eleven", 9 "nine", 5 "five", 14 "fourteen", 16 "sixteen", 30 "thirty", 10 "ten", 18 "eighteen", 80 "eighty", 8 "eight"})

(declare number)

(defn div-mod [n f]
  (let [[q m] [(quot n f) (mod n f)]]
    (str (number q) " " (nset f) (when (> m 0) (str " " (number m))))))

(defn number [n]
  (cond
    (or (> n 999999999999) (< n 0)) (throw (IllegalArgumentException.))
    (>= n 1000000000) (div-mod n 1000000000)
    (>= n 1000000) (div-mod n 1000000)
    (>= n 1000) (div-mod n 1000)
    (>= n 100) (div-mod n 100)
    (> n 20) (str (nset (* 10 (quot n 10))) "-" (nset (mod n 10)))
    :else (nset n)))

;; first version

;; (ns say (:require [clojure.string :refer [join trim]]))
;; (def nums (concat (range 21) '(30 40 50 60 70 80 90 100 1000 1000000 1000000000)))
;; (def chas ["zero" "one" "two" "three" "four" "five" "six" "seven" "eight" "nine"
;;            "ten" "eleven" "twelve" "thirteen" "fourteen" "fifteen" "sixteen" "seventeen" "eighteen" "nineteen"
;;            "twenty" "thirty" "forty" "fifty" "sixty"  "seventy" "eighty" "ninety" "hundred" "thousand" "million" "billion"])
;; (def nset (zipmap nums chas))
;; (defn expt [n m] (reduce * (repeat m n)))
;; (defn toint [n m] (* m (quot n m)))

;; (defn hundred [x]
;;   (str " "
;;        (cond  (= x 0) ""
;;               (= 3 (count (str x))) (str (nset (quot x 100)) " hundred" (hundred (- x (toint x 100))))
;;               (and (= 2 (count (str x))) (= \1 (first (str x)))) (nset x)
;;               (and (= 2 (count (str x))) (not (= \0 (last (str x))))) (str (nset (toint x 10)) "-" (nset (mod x 10)))
;;               :else (nset x))))

;; (defn numberX [n]
;;   (let [x (count (str n))]
;;     (-> (cond
;;           (or (> x 12) (< n 0)) (throw (IllegalArgumentException.))
;;           (> x 9) (str (hundred (quot n (expt 10 9))) " billion " (numberX (- n (toint n (expt 10 9)))))
;;           (> x 6) (str (hundred (quot n (expt 10 6))) " million " (numberX (- n (toint n (expt 10 6)))))
;;           (> x 3) (str (hundred (quot n (expt 10 3))) " thousand " (numberX (- n (toint n (expt 10 3)))))
;;           (> x 0) (hundred n))
;;         (str " ")
;;         trim)))

;; (defn number [n]
;;   (if (= 0 n) "zero"
;;       (numberX n)))

;; jaihindhreddy's solution
;; (ns say
;;   (:require [clojure.string :as str]))
;; (def ^:private implicits
;;   {0 "zero"
;;    1 "one"
;;    2 "two"
;;    3 "three"
;;    4 "four"
;;    5 "five"
;;    6 "six"
;;    7 "seven"
;;    8 "eight"
;;    9 "nine"
;;    10 "ten"
;;    11 "eleven"
;;    12 "twelve"
;;    13 "thirteen"
;;    14 "fourteen"
;;    15 "fifteen"
;;    16 "sixteen"
;;    17 "seventeen"
;;    18 "eighteen"
;;    19 "nineteen"
;;    20 "twenty"
;;    30 "thirty"
;;    40 "forty"
;;    50 "fifty"
;;    60 "sixty"
;;    70 "seventy"
;;    80 "eighty"
;;    90 "ninety"})
;; (def ^:private ^:const mags
;;   [[1000000000 "billion"]
;;    [1000000    "million"]
;;    [1000       "thousand"]
;;    [100        "hundred"]])
;; (defn- -number [n]
;;   (cond (contains? implicits n) (implicits n)
;;         (< n 100) (let [r (rem n 10)]
;;                       (str (-number (- n r)) "-" (-number r)))
;;         :else
;;          (let [[[limit denom]] (drop-while (fn [[l]] (< n l)) mags)
;;                [qs r]          ((juxt (comp -number quot) rem) n limit)
;;                rs              (when-not (zero? r) (-number r))]
;;            (str/trimr (str/join " " [qs denom rs])))))
;; (defn number [n]
;;   (if (not (<= 0 n 999999999999))
;;     (throw (IllegalArgumentException.))
;;     (-number n)))
