(ns meetup)

(def week_map {1 {:tuesday 31} 2 {:friday 28} 3 {:friday 31} 4 {:monday 30} 5 {:wednesday 31} 6 {:saturday 30} 7 {:monday 31} 8 {:thursday 31} 9 {:sunday 30} 10 {:tuesday 31} 11 {:friday 30} 12 {:sunday 31}})

(def week_list {:monday 1 :tuesday 2 :wednesday 3 :thursday 4 :friday 5 :saturday 6 :sunday 7})
(def seq_list {:first 1 :second 2 :third 3 :fourth 4 :last 5})

(defn change_kv [m]
  (->> (for [[k v] m]
         [v k])
       (into {})))

(defn teenth_day [m]
  (let [day_13 (dec (mod (+ 6 (week_list (key (first (week_map m))))) 7))]
    (zipmap (map #((change_kv week_list) %)
                 (map #(cond (> % 7) (- % 7) (<= % 0) (+ % 7) :else %)
                      (range day_13 (+ 7 day_13))))
            (range 13 20))))

(defn meetup [m y w n]
  (if (= n :teenth) [y m ((teenth_day m) w)]
    (let [nday (- (week_list w) (week_list (key (first (week_map m)))))
          day (+ (* (dec (seq_list n)) 7)
                 (if (< nday 0) (if (some #(= % m) [3 5 6 8 9 12])
                                  (inc (+ 7 nday))
                                  (inc (+ 14 nday))) (inc nday)))]
      [y m (if (> day (second (first (week_map m)))) (- day 7) day)])))


