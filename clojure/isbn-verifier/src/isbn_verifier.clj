(ns isbn-verifier)

(defn isbn? [s]
  (let [l (re-seq #"\d|X" s)]
    (if (or (some #(= % "X") (drop-last l))
            (not (= (count l) 10))) false
        (->>
         (map #(if (= % "X") 10 (read-string %)) l)
         (map * (reverse (range 1 11)))
         (reduce +)
         (#(mod % 11))
         zero?))))


;;


;; (def imap (reverse (range 1 11)))
;; (def iset {"0" 0 "1" 1 "2" 2 "3" 3 "4" 4 "5" 5 "6" 6 "7" 7 "8" 8 "9" 9 "X" 10})
;; (defn isbn? [s]
;;   (let [l (re-seq #"\d|X" s)]
;;     (if (or (some #(= % "X") (drop-last l)) (not (= (count l) 10))) false
;;         (if (= 0 (mod (reduce + (map * imap (map iset l))) 11))
;;           true false))))
