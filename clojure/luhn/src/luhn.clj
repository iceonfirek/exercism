(ns luhn (:require [clojure.string :refer [replace]]))

(defn d_p [[f s]]
  (if (nil? s) f
      (+ f (if (< 9 (* 2 s)) (- (* 2 s) 9) (* 2 s)))))

(defn valid? [s]
  (let [x (replace s " " "")]
    (if (= x "0") false
        (->> (reverse x)
             (map #(- (int %) 48))
             (partition-all 2 2)
             (map d_p)
             (apply +)
             (#(zero? (mod % 10)))))))
