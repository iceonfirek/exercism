(ns robot-name)

(defn rand-str [len]
  (apply str (take len (repeatedly #(char (+ (rand 26) 65))))))

(defn rand-int [len]
  (apply str (take len (repeatedly #(char (+ (rand 10) 48))))))

(defn robot [] ;; <- arglist goes here
  (atom {:name (str (rand-str 2) (rand-int 3))})
  )


(defn robot-name [x] ;; <- arglist goes here
  (:name @x)
  )

(defn reset-name [x]
  (swap! x #(assoc % :name (str (rand-str 2) (rand-int 3))))
  )
