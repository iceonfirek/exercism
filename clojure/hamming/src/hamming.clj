(ns hamming)

(defn distance
  ([strand1 strand2]
   (if (= (count strand1) (count strand2))
     (distance strand1 strand2 (- (count strand1) 1))
     nil)) 
  ([strand1 strand2 n]
   (if (< n 0) 0
       (if (= (nth strand1 n) (nth strand2 n)) (distance strand1 strand2 (- n 1))
           (+ 1 (distance strand1 strand2 (- n 1))))))
  
  )
