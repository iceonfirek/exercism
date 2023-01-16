(ns spiral-matrix)

(def xxx [1 4 5 8 11 23 33])
(def yyy [1 3 4 7 8 11 22 54])
(def zzz [2 6 9 13 15 17 24])

(defn merge-s [x y z l]
  (let [
        a (if (not= [] x) (first x) nil)
        b (if (not= [] y) (first y) nil)
        c (if (not= [] z) (first z) nil)
        ]

    (cond
      (and (= x []) (= y []) (= z [])) l
      (and (<= a b) (<= a c)) (merge-s (rest x) y z (conj l a))
      (and (<= b a) (<= b c)) (merge-s x (rest y) z (conj l b))
      (and (<= c b) (<= c a)) (merge-s x y (rest z) (conj l c))
      

      ))


  )
