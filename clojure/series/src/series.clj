(ns series)

(defn slices [string length] 
  (into []
        (if (= 0 length) [""]
            (for [i (range (- (count string) (- length 1)))]
              (subs string i (+ i length))))))
