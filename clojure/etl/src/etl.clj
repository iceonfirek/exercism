(ns etl)

(require '[clojure.string :refer [lower-case]])

(defn transform [source] ;; <- arglist goes here
  (into {}
       (for [[key value] source
             letter value]
         [(lower-case letter) key]))
  )
