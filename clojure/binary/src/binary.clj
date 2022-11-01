(ns binary)

(defn exp [z n]
  (if (= 0 n) 1 (* z (exp z (dec n)))))

(defn to-decimal
  ([s] (to-decimal s (- (count s) 1) 0))
  ([s i j]
   (cond (< i 0) 0
         (= \1 (nth s j)) (+ (to-decimal s (- i 1) (+ j 1)) (exp 2 i))
         :else (+ (to-decimal s (- i 1) (+ j 1)) 0))))
