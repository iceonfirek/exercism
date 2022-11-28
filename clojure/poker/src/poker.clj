(ns poker (:require [clojure.string :as str]))

(def poker_map (zipmap ["2" "3" "4" "5" "6" "7" "8" "9" "10" "J" "Q" "K" "A"] (range 2 15)))
(def flower_map (zipmap ["C" "D" "H" "S"] (range 1 5)))
;; Spade Heart Diamond Club
(defn deal_str [s]
  (->> (str/replace s #"[S|H|D|C]" "")
       (#(str/split % #" "))
       (map poker_map) sort))
(defn deal_flower [s]
  (->> (str/replace s #"[^S|H|D|C| ]" "")
       (#(str/split % #" "))
       (map flower_map) sort))
(defn equal_list [s]
  (reduce #(if (= %2 %1) %2 0) s))
(defn equal_list? [s]
  (= (last s) (equal_list s)))
(defn all_cpr [f s]
  (let [l (reduce #(f %1 %2) s)]
    (reduce #(if (= (deal_str l) (deal_str %2)) (conj %1 %2) %1) [] s)))
(defn value_cpr [f a b]
  (let [x (f a)
        y (f b)]
    (loop [x1 x
           y1 y]
      (cond (= x1 []) a
            (> (first x1) (first y1)) a
            (< (first x1) (first y1)) b
            :else (recur (rest x1) (rest y1))))))
(defn non_straight_value_cpr [f a b]
  (let [x (f a)
        y (f b)
        z (map - x y)]
    (loop [i 0]
      (cond (= i 5) a
            (> (nth z i) 0) a
            (< (nth z i) 0) b
            :else (recur (inc i))))))
(defn is_straight_value_cpr [f a b]
  (let [x (f a)
        y (f b)]
    (if (>= x y) a b)))
(defn bigger_num [a b]
  (if (> a b) a b))
;; straight_flush
;; all done: flower compare
(defn straight_flush [x]
  (let [s (deal_str x)
        f (equal_list (deal_flower x))
        l (reduce #(if (= %2 (inc %1)) (inc %1) 0) s)]
    (cond (and (not= 0 f) (= l (last s))) x
          (and (not= 0 f) (= s [2 3 4 5 14])) x
          :else nil)))
(defn straight_flush_cpr_value [x]
  (first (deal_str x)))
(defn straight_flush_value_cpr [a b]
  (is_straight_value_cpr straight_flush_cpr_value a b))
(defn straight_flush_cpr [s]
  (all_cpr straight_flush_value_cpr s))

;;four_of_a_kind
;;all done
(defn four_of_a_kind [x]
  (let [s (deal_str x)
        l (equal_list (rest s))
        m (equal_list (drop-last s))]
    (if (or (not= 0 l) (not= 0 m))
      x nil)))
(defn four_of_a_kind_cpr_value [x]
  (let [s (deal_str x)
        l (equal_list (rest s))
        m (equal_list (drop-last s))]
    (cond (= l 0) [m (last s)]
          (= m 0) [l (first s)])))
(defn four_of_a_kind_value_cpr [a b]
  (value_cpr four_of_a_kind_cpr_value a b))
(defn four_of_a_kind_cpr [s]
  (all_cpr four_of_a_kind_value_cpr s))

;;full_house
;;done
(defn full_house [x]
  (let [s (deal_str x)]
    (cond (and (equal_list? (take 3 s)) (equal_list? (take-last 2 s))) x
          (and (equal_list? (take 2 s)) (equal_list? (take-last 3 s))) x
          :else nil)))
(defn full_house_cpr_value [x]
  (let [s (deal_str x)]
    (cond (and (equal_list? (take 3 s)) (equal_list? (take-last 2 s))) [(first s) (last s)]
          (and (equal_list? (take 2 s)) (equal_list? (take-last 3 s))) [(last s) (first s)]
          :else nil)))
(defn full_house_value_cpr [a b]
  (value_cpr full_house_cpr_value a b))
(defn full_house_cpr [s]
  (all_cpr full_house_value_cpr s))

;;flush
;;done
(defn flush [x]
  (let [s (deal_flower x)]
    (if (equal_list? s) x nil)))
(defn flush_cpr_value [x]
  (reverse (deal_str x)))
(defn flush_value_cpr [a b]
  (non_straight_value_cpr flush_cpr_value a b))
(defn flush_cpr [s]
  (all_cpr flush_value_cpr s))

;;straight
(defn straight [x]
  (let [s (deal_str x)
        l (reduce #(if (= %2 (inc %1)) (inc %1) 0) s)]
    (cond (= l (last s)) x
          (= s [2 3 4 5 14]) x
          :else nil)))
(defn straight_cpr_value [x]
  (first (deal_str x)))
(defn straight_value_cpr [a b]
  (is_straight_value_cpr straight_cpr_value a b))
(defn straight_cpr [s]
  (all_cpr straight_value_cpr s))

;; three_of_a_kind
(defn three_of_a_kind_att [x]
  (let [s (deal_str x)
        [f se th fo fi] s]
    (cond (= se th fo) [x [se (bigger_num f fi)]]
          (= f se th) [x [se (bigger_num fo fi)]]
          (= th fo fi) [x [th (bigger_num f se)]]
          :else nil)))
(defn three_of_a_kind [x]
  (first (three_of_a_kind_att x)))
(defn three_of_a_kind_cpr_value [x]
  (second (three_of_a_kind_att x)))
(defn three_of_a_kind_value_cpr [a b]
  (value_cpr three_of_a_kind_cpr_value a b))
(defn three_of_a_kind_cpr [x]
  (all_cpr three_of_a_kind_value_cpr x))

;;two_pairs
(defn two_pairs_att [x]
  (let [s (deal_str x)
        [f se th fo fi] s]
    (cond (and (= f se) (= th fo)) [x [fo f fi]]
          (and (= f se) (= fo fi)) [x [fo f th]]
          (and (= se th) (= fo fi)) [x [fo se f]]
          :else nil)))
(defn two_pairs [x]
  (first (two_pairs_att x)))
(defn two_pairs_cpr_value [x]
  (second (two_pairs_att x)))
(defn two_pairs_value_cpr [a b]
  (value_cpr two_pairs_cpr_value a b))
(defn two_pairs_cpr [x]
  (all_cpr two_pairs_value_cpr x))

;;pairs
(defn pairs_att [x]
  (let [s (deal_str x)
        [f se th fo fi] s]
    (cond (= f se) [x [f th fo fi]]
          (= se th) [x [se f fo fi]]
          (= th fo) [x [fo f se fi]]
          (= fo fi) [x [fi f se th]]
          :else nil)))
(defn pairs [x]
  (first (pairs_att x)))
(defn pairs_cpr_value [x]
  (second (pairs_att x)))
(defn pairs_value_cpr [a b]
  (value_cpr pairs_cpr_value a b))
(defn pairs_cpr [x]
  (all_cpr pairs_value_cpr x))

;;tie
(defn tie [x] x)
(defn tie_cpr_value [x]
  (deal_str x))
(defn tie_value_cpr [a b]
  (value_cpr tie_cpr_value a b))
(defn tie_cpr [x]
  (all_cpr tie_value_cpr x))

;;main
(defn apply_rules [rules s]
  (remove nil? (map rules s)))

(def rules [straight_flush four_of_a_kind full_house flush straight three_of_a_kind two_pairs pairs tie])
(def cprs [straight_flush_cpr four_of_a_kind_cpr full_house_cpr flush_cpr straight_cpr three_of_a_kind_cpr two_pairs_cpr pairs_cpr tie_cpr])
(def cpr_map (zipmap rules cprs))
(defn best-hands [hands]
  (loop [i 0]
    (let [y (apply_rules (nth rules i) hands)]
      (if (not= [] y) ((cpr_map (nth rules i)) y)
          (recur (inc i))))))




