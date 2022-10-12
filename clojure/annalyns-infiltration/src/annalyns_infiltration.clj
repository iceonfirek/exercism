(ns annalyns-infiltration)

;; (def knight-awake? true)

(defn can-fast-attack?
  "Returns true if a fast-attack can be made, false otherwise."
  [knight-awake?]
  (if (not knight-awake?) true
      false
  ))

;; (def knight-awake? false)
;; (def archer-awake? true)
;; (def prisoner-awake? false)

(defn can-spy?
  "Returns true if the kidnappers can be spied upon, false otherwise."
  [knight-awake? archer-awake? prisoner-awake?]
  (cond (or knight-awake? archer-awake? prisoner-awake?) true
        :else false
  ))

;; (def archer-awake? false)
;; (def prisoner-awake? true)

(defn can-signal-prisoner?
  "Returns true if the prisoner can be signalled, false otherwise."
  [archer-awake? prisoner-awake?]
  (cond (and (not archer-awake?) prisoner-awake?) true
        :else false
  ))
;; (def knight-awake? false)
;; (def archer-awake? true)
;; (def prisoner-awake? false)
;; (def dog-present? false)

(defn can-free-prisoner?
  "Returns true if prisoner can be freed, false otherwise."
  [knight-awake? archer-awake? prisoner-awake? dog-present?]
  (cond (and dog-present? (not archer-awake?)) true
        (and prisoner-awake? (not archer-awake?) (not knight-awake?) (not dog-present?)) true
        :else false))
