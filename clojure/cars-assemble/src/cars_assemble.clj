(ns cars-assemble)

(defn speed-rate [speed]
    (cond (= speed 0) 0.0
        (and (> speed 0) (< speed 5)) 1.0
        (and (> speed 4) (< speed 9)) 0.9
        (= speed 9) 0.8
        (= speed 10) 0.77))

(def cars-per-hour 221)

(defn production-rate
  [speed]
  (* cars-per-hour speed (speed-rate speed)))

(defn working-items
  "Calculates how many working cars are produced per minute"
  [speed]
  (int (quot (production-rate speed) 60)
  ))
