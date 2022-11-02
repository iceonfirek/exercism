(ns grade-school)

(defn grade [school grade]
  (school grade []))

(defn add [school name grade]
  (into school
        {grade ((fnil conj []) (school grade) name)}))

(defn sorted [school]
  (into {} (sort (for [[k v] school] [k (sort v)]))))

;; (defn add [school name grade]
;;   (if (contains? school grade)
;;     (into school {grade (conj (school grade) name)})
;;     (into school {grade [name]})))

;; (defn grade [school grade]yd
;;   (if (empty? school) [] (school grade)))
