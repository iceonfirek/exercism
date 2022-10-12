(ns interest-is-interesting)

(defn interest-rate
  "TODO: add docstring"
  [balance]
  (cond (>= balance 5000) 2.475
        (>= balance 1000) 1.621
        (and (>= balance 0) (< balance 1000)) 0.5
        :else -3.213
  ))

(defn annual-balance-update
  "TODO: add docstring"
  [balance]
  (* balance (+ 1 (bigdec (* 0.01 (Math/abs (interest-rate balance)))))))
  

(defn amount-to-donate
  "TODO: add docstring"
  [balance tax-free-percentage]
  (cond (neg? balance) 0
        :else (int (* 2 balance 0.01 tax-free-percentage))))
