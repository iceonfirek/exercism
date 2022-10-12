(ns elyses-destructured-enchantments)

(defn first-card
  "Returns the first card from deck."
  [[first]]
  first
)

(defn second-card
  "Returns the second card from deck."
  [[_ second]]
  second)

(def deck [5 9 7 1 8])

(defn swap-top-two-cards
  "Returns the deck with first two items reversed."
  [[first second & others]]
  (into [second first] others))

(defn discard-top-card
  "Returns a sequence containing the first card and
   a sequence of the remaining cards in the deck."
  [[first & others]]
  [first others])

(def face-cards
  ["jack" "queen" "king"])

(defn insert-face-cards
  "Returns the deck with face cards between its head and tail."
  [[first & others]]
  (if (nil? first) face-cards
    (into [first] (into face-cards others))))
