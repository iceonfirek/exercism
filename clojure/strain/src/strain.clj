(ns strain)

(defn retain [f x]
  (for [v x
        :when (f v)]
    v))

(defn discard [f x]
  (retain (comp not f) x))



;; (defn retain [f x]
;;   (cond
;;     (empty? x) []
;;     (f (first x)) (cons (first x) (retain f (next x)))
;;     :else (retain f (next x))))

;; (defn discard [f x]
;;   (retain (comp not f) x))
  



;; (defn retain [f x]
;;   (into []
;;         (filter #(f %) x))
;; )

;; (defn discard [f x]
;;   (into []
;;         (remove #(f %) x))
;; )
