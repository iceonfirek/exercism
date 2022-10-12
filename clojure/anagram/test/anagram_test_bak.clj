;; (loop [i (count prospect-list)]
  ;;   (if (> i 0)
  ;;     (if (is-equal? word (first prospect-list)) (conj [] (first prospect-list)))
  ;;     (recur (dec i)))
  ;;   (anagrams-for word (rest prospect-list))
  ;;   ))
 ;; (let [x []]
 ;;  (if (> (count prospect-list) 0)
 ;;    (do
 ;;      (if (is-equal? word (chunk-first prospect-list))
 ;;        (do (conj x (chunk-first prospect-list))
 ;;            (anagrams-for word (chunk-rest prospect-list)))
 ;;        (anagrams-for word (chunk-rest prospect-list)))
 ;;      (anagrams-for word (chunk-rest prospect-list)))
 ;;    (conj x))))
 

   
   ;; (if (is-equal? word (first prospect-list))
   ;;   (cons (first prospect-list) (anagrams-for word (rest prospect-list))
   ;;         (anagrams-for word (rest prospect-list)))))
   

  ([pred coll]
   (lazy-seq
    (when-let [s (seq coll)]
      (if (chunked-seq? s)
        (let [c (chunk-first s)
              size (count c)
              b (chunk-buffer size)]
          (dotimes [i size]
              (let [v (.nth c i)]
                (when (pred v)
                  (chunk-append b v))))
          (chunk-cons (chunk b) (filter pred (chunk-rest s))))
        (let [f (first s) r (rest s)]
          (if (pred f)
            (cons f (filter pred r))
            (filter pred r))))))

  (when-let [s (seq prospect-list)]
    (let [size (count s)
          b []]
      (dotimes [i size]
        (let [v (nth s i)]
          (when (is-equal? word v)
            (conj b v)))
        (anagrams-for word (rest s)))
      ))
    )
