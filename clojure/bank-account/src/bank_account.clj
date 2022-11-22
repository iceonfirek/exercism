(ns bank-account)

(defn open-account [] (atom 0))

(defn close-account [x]
  (reset! x nil))

(defn get-balance [x]
  @x)

(defn update-balance [x y]
  (swap! x + y))

;;atom swap! reset! 
;;refer object88's solution
