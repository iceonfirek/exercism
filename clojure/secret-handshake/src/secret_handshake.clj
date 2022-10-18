(ns secret-handshake)

(defn commands [x]
  (if (>= (quot x 32) 1) []
      (cond-> []
        (= 1 (mod x 2)) (conj "wink")
        (= 1 (quot (mod (mod (mod x 16) 8) 4) 2)) (conj "double blink")
        (= 1 (quot (mod (mod x 16) 8) 4)) (conj "close your eyes")
        (= 1 (quot (mod x 16) 8)) (conj "jump")
        (= 1 (quot x 16)) reverse)))
