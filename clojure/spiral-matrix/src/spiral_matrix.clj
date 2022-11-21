(ns spiral-matrix)

(defn getv [x y n]
  (if (<= x y)
    (let [k (min x (- n y 1))]
      (+ (* 4 k (- n k)) (+ 1 (- (+ x y) (* 2 k)))))
    (let [k (inc (min y (- n x 1)))]
      (+ (* 4 k (- n k)) (- 1 (- (+ x y) (* 2 (dec k))))))))

(defn spiral [n]
  (->> (for [row (range n)
             coll (range n)]
         (getv row coll n))
       (partition n)))

;;reference
;;https://www.cnblogs.com/flyinghearts/archive/2010/12/25/1916574.html

;;in-out-spiral

;; (defn getv_in [x y n]
;;   (if (odd? n)
;;     (- (+ 1 (* n n)) (getv x (- n 1 y) n))
;;     (- (+ 1 (* n n)) (getv (- n 1 x) y n))))

;; (defn spiral_in [n]
;;   (->> (for [row (range n)
;;              coll (range n)]
;;          (getv_in row coll n))
;;        (partition n)))
