(ns bird-watcher)

(def last-week 
  [0 2 5 3 7 8 4])

(def birds-per-day [2 5 0 7 4 1])

(defn today [birds]
  (last birds))

(defn inc-bird [birds]
  (conj (pop birds) (inc (last birds))))
  
(defn day-without-birds? [birds]
  (cond (some #{0} birds) true
        :else             false))

 (defn n-days-count [birds n]
   (if (< (- n 1) 0)
     0
     (+ (get birds (- n 1)) (n-days-count birds (- n 1)))))

(defn busy-days [birds]
  (count (filter #(>= % 5) birds)))

(defn odd-week? [birds]
  (= birds [1 0 1 0 1 0 1]))
