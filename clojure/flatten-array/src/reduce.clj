(reduce (fn [p [k v]] (into p {k (+ 1 v)})) {} {:a 2 :b 3})

(reduce (fn [[n d] b]
          [(+ n b)
           (inc d)])
        [0 0]
        [1 2 3 4 5])

(reduce (fn [[a c] d] [(+ a d) (+ 1 c)] ) [1 2 3] [4 5 6])
