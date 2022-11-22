(ns robot-simulator)

(def dir_map {:east 0 :north 90 :west 180 :south 270})

(defn hm [x m]
  (some (fn [[k v]] (if (= v x) k)) m))

(defn robot [x y]
  {:bearing y :coordinates x})

(defn advance [c]
  (let [dir (:bearing c)
        coord (:coordinates c)
        x (:x coord)
        y (:y coord)]
    (cond
      (= dir :north) {:bearing :north :coordinates {:x x :y (inc y)}}
      (= dir :south) {:bearing :south :coordinates {:x x :y (dec y)}}
      (= dir :east) {:bearing :east :coordinates {:x (inc x) :y y}}
      (= dir :west) {:bearing :west :coordinates {:x (dec x) :y y}})))

(defn turn-right [x]
  (-> (- (x dir_map) 90)
      (mod 360)
      (hm dir_map)))

(defn turn-left [x]
  (-> (+ (x dir_map) 90)
      (mod 360)
      (hm dir_map)))

(defn simulate_ [s c]
  (let [m (first s)
        dir (:bearing c)
        coord (:coordinates c)]
    (cond
      (= s []) c
      (= m \R) (simulate_ (rest s) {:bearing (turn-right dir) :coordinates coord})
      (= m \L) (simulate_ (rest s) {:bearing (turn-left dir) :coordinates coord})
      (= m \A) (simulate_ (rest s) (advance c)))))

(defn simulate [s c] (simulate_ (seq s) c))


;;Nto's reduce solution
;; (ns robot-simulator)
;; (defn robot [coordinates bearing] ;; <- arglist goes here
;;   {:coordinates coordinates, :bearing bearing})
;; (defn advance [{:keys [coordinates bearing]}]
;;   (let [xpos (:x coordinates) ypos (:y coordinates)]
;;     (cond (= bearing :north) (robot {:x xpos :y (inc ypos)} bearing)
;;           (= bearing :south) (robot {:x xpos :y (dec ypos)} bearing)
;;           (= bearing :east) (robot {:x (inc xpos) :y ypos} bearing)
;;           (= bearing :west) (robot {:x (dec xpos) :y ypos} bearing))))
;; (defn turn-right [direction] ;; <- arglist goes here
;;   ;; your code goes here
;;   (get {:north :east, :east :south, :south :west, :west :north} direction))
;; (defn turn-left [direction] ;; <- arglist goes here
;;   ;; your code goes here
;;   (get {:north :west, :west :south, :south :east, :east :north} direction))
;; ;; AHHHHHHHHHH, I've inverted instructions and robot-start, this taken me 30 minutes!
;; (defn simulate [instructions robot-start] ;; <- arglist goes here
;;   (reduce (fn [{:keys [coordinates bearing] :as robot-state } instruction]
;;             (cond (= \R instruction) (robot coordinates (turn-right bearing))
;;                   (= \L instruction) (robot coordinates (turn-left bearing))
;;                   (= \A instruction) (advance robot-state)))
;;           robot-start
;;           (seq instructions)))
