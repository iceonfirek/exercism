(ns nucleotide-count)

(defn count-of-nucleotide-in-strand [nucleotide strand] ;; <- Arglist goes here
 ;; {:pre [(contains? #{\A \T \G \C} nucleotide)]}
  (if (contains? #{\A \T \G \C} nucleotide)
    (count (filter #(= nucleotide %) strand))
    (throw (Exception.)))
  )


(defn nucleotide-counts [strand] ;; <- Arglist goes here
  (into {}
        (for [key [\A \T \G \C]]
          [key (count (filter #(= key %) strand))]
          ))
  )
