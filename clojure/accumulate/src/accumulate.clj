(ns accumulate)

(defn accumulate [f, coll]
  (if (> (count coll) 0)
    (cons (f (first coll)) (accumulate f (rest coll)))
    []
    )
  )
