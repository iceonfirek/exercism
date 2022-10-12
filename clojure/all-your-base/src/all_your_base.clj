(ns all-your-base)

(defn- to-base-10 [n-list from-base]
  (reduce #(+ (* from-base %1) %2) 0 n-list))

(defn- from-base-10 [n to-base]
  (loop [n n res ()]
    (let [q (quot n to-base)
          res (cons (rem n to-base) res)]
      (if (zero? q)
        res
        (recur q res)))))

(defn- guard [n-list from-base to-base]
  (and (seq n-list)
       (> to-base 1)
       (every? #(and (number? %)
                     (<= 0 % (dec from-base)))
               n-list)))
(defn convert [from-base n-list to-base]
  (when (guard n-list from-base to-base)
    (-> n-list
        (to-base-10 from-base)
        (from-base-10 to-base))))
