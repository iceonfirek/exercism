(ns go-counting)
(defn nth-in [s [a b]]
  (nth (nth s b) a))
(defn value_identify [[a b] m l]
  (or (= a m) (< a 0) (< b 0)  (= b l)))
(defn value_range [[x y] m l]
  (->> (for [a [(dec x) (inc x)]
             b [(dec y) (inc y)]]
         [[x b] [a y]])
       (apply concat)
       (into #{})
       (remove #(value_identify % m l))))
(defn env_identify [s [x y] stone_set]
  (->> (for [[a b] (value_range [x y] (count (first s)) (count s))]
         (cond (= (nth-in s [a b]) \B) [:black]
               (= (nth-in s [a b]) \W) [:white]
               (and (not (some #(= % [a b]) stone_set)) (= (nth-in s [a b]) \space))
               (env_identify s [a b] (into stone_set [[a b]]))
               :else []))
       (apply concat [[x y]])
       (into #{})))
(defn territory [grid [x y]]
  (cond (value_identify [x y] (count (first grid)) (count grid)) (throw (Throwable.))
        (not (= (nth-in grid [x y]) \space)) {:stones #{} :owner nil}
        :else (let [s (env_identify (map seq grid) [x y] [])
                    stones (remove #(or (= % :black) (= % :white)) s)
                    owner (filter #(or (= % :black) (= % :white)) s)]
                {:stones (into #{} stones) :owner (if (= 1 (count owner)) (first owner) nil)})))
(defn change_kv [p]
  (let [[f s] (for [[k v] p] v)]
    (def color_map {nil :null-territory :black :black-territory :white :white-territory})
    {(color_map s) f}))

(defn merge_sets [set]
  (for [key [:white-territory
             :black-territory
             :null-territory]]
    (let [s (map key set)]
      {key (into #{} (apply concat s))})))
(defn territories [grid]
  (->> (let [s (map seq grid)]
         (for [a (range (count (first s)))
               b (range (count s))]
           (cond
             (= (nth-in s [a b]) \B) nil
             (= (nth-in s [a b]) \W) nil
             :else (territory grid [a b]))))
       (remove nil?)
       (map change_kv)
       merge_sets
       (apply clojure.set/union)))


