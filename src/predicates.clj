(ns predicates)

(defn sum-f [f g x]
  (+ (f x) (g x)))

(defn less-than [n]
  (fn [k] (< k n)))

(defn equal-to [n]
  (fn [k] (== k n)))

(defn set->predicate [a-set]
  (fn [a-key] (contains? a-set a-key)))

(defn pred-and [pred1 pred2]
  (fn [x] (and (pred1 x) (pred2 x))))

(defn pred-or [pred1 pred2]
  (fn [x] (or (pred1 x) (pred2 x))))

(defn whitespace? [character]
  (Character/isWhitespace character))

(defn blank? [string]
  (or
   (empty? string)
   (= string nil)
   (every? whitespace? string)))

(defn has-award? [book award]
  (contains? (:awards book) award))

(defn HAS-ALL-THE-AWARDS? [book awards]
  (let [has? (fn [a] (has-award? book a))]
    (every? has? awards)))

(defn my-some [pred a-seq]
  (let [truthy? (fn [v] (if v true false))]
      (first (filter truthy? (map pred a-seq)))))

(defn my-every? [pred a-seq]
  (let [falsey? (fn [v] (if (not v) true false))]
    (empty? (filter falsey? (map pred a-seq)))))

(defn prime? [n]
  (if (= n 1)
    false
    (let [divisible (fn [d] (= (mod n d) 0))]
      (not (some divisible (range 2 (- n 1)))))))
;^^
