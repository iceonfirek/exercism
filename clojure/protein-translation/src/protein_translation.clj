(ns protein-translation)
(def pset {"Methionine" ["AUG"], "Phenylalanine" ["UUU" "UUC"], "Leucine" ["UUA" "UUG"], "Serine" ["UCU" "UCC" "UCA" "UCG"], "Tyrosine" ["UAU" "UAC"], "Cysteine" ["UGU" "UGC"], "Tryptophan" ["UGG"], "STOP" ["UAA" "UAG" "UGA"]})

(def translate-codon
  (into {}
        (for [[key values] pset
              value values]
          [value key])))

(defn translate-rna [rna]
  (take-while #(not= "STOP" %) (map translate-codon (re-seq #"..." rna))))
