(ns ttt-clojure.board)

  (defn create-board [size]
    (vec (repeat (* size size) "-")))

  (defn valid? [board loc]
    (try
      (= (nth board loc) "-")
      (catch Exception e false)))

  (defn place-piece [board mark loc]
    (if (valid? board loc)
      (assoc board loc mark)
      "invalid"))

  (defn possible-moves [board]
    (for [x (range 9)
        :when (valid? board x)]
          x))
