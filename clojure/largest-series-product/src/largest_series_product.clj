(ns largest-series-product)

(defn toint [x]
  (map #(- (if (< (int %) 58) (int %)) 48) x))
(defn part [n x]
  (map #(toint %) (partition n 1 x)))
(defn largest-product [n s]
  (if (= n 0) 1
      (->> (map #(apply * %) (part n s))
           (reduce #(if (> %1 %2) %1 %2)))))
