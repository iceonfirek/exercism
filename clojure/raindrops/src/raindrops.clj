(ns raindrops)

;; (defn convert [n]
;;   (def result "")
;;   (when (= 0 (mod n 3)) (def result (str result "Pling")))
;;   (when (= 0 (mod n 5)) (def result (str result "Plang")))
;;   (when (= 0 (mod n 7)) (def result (str result "Plong")))
;;   (when (= result "") (def result (str n)))
;;   result
;;   )

(defn convert [n]
  (cond-> nil
    (= 0 (mod n 3)) (str "Pling")
    (= 0 (mod n 5)) (str "Plang")
    (= 0 (mod n 7)) (str "Plong")
    :always (or (str n))
    ))
