(ns bob (:require [clojure.string :refer [includes? split replace]]))

(defn AllCap [b]
  (filter #(re-matches #"([A-Z]+)" %) (split b #"")))
(defn AllLet [c]
  (filter #(re-matches  #"([A-Z]+)|([a-z]+)" %) (split c #"")))
(defn Alll [d]
  (filter #(re-matches  #"([A-Z]+)|([a-z]+)|([0-9]+)" %) (split d #"")))
(defn Laststr [e]
  (last (split (replace e #" " "") #"")))

(defn isAllCap? [a]
  (and (some char? a) (= (AllCap a) (AllLet a)) (not= () (AllLet a))))


(defn response-for [s] ;; <- arglist goes here
  (cond
    (and (isAllCap? s) (includes? s "?")) "Calm down, I know what I'm doing!"
    (and (= "?" (Laststr s)) (includes? s "?")) "Sure."
    (= () (Alll s)) "Fine. Be that way!"
    (isAllCap? s) "Whoa, chill out!"
    :else "Whatever."
  ))
