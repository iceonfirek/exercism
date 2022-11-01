(ns binary-search-tree)

(defn value [x]
  (first x))

(defn left [s]
  (second s))

(defn right [s]
  (last s))

(defn singleton [x]
  (list x '() '()))

(defn insert [x s]
  (cond (empty? s) (singleton x)
;;      (= x (value s)) s
        (<= x (value s)) (list (value s) (insert x (left s)) (right s))
        (> x (value s)) (list (value s) (left s) (insert x (right s)))))

(defn to-list [s]
  (if (not (empty? s))
      (concat (to-list (left s)) [(value s)] (to-list (right s)))))

(defn from-list [s]
  (if (= 1 (count s)) (singleton (value s))
      (insert (last s) (from-list (pop s)))))

;; (defn from-list [s]
;;   (reduce #(insert %2 %1) nil s))

