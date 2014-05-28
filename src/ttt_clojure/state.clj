(ns ttt-clojure.state
  (:require [ttt-clojure.board  :as board]))

  (defn full? [board]
    (= (board/possible-moves board) () ))

  (defn opposing? [player]
    (if (= player "x")
        "o"
        (if (= player "o")
            "x")))

  (defn horizontal? [board mark]
    (loop [i 0]
    (when (< i 3)
    (if (= (nth (partition 3 board) i) (repeat 3 mark))
      true
    (recur (inc i))))))

  (defn diagonal1? [board mark]
    (if (= (flatten (partition 1 4 board)) (repeat 3 mark))
      true))

  (defn diagonal2? [board mark]
    (if (= (flatten (partition 1 2 (subvec board 2 7))) (repeat 3 mark))
      true))

  (defn vertical? [board mark]
    (loop [i 0]
    (when (< i 3)
    (if (= (flatten (partition 1 3 (subvec board i 9))) (repeat 3 mark))
      true
    (recur (inc i))))))

  (defn winner? [board mark]
    (or
      (horizontal? board mark)
      (diagonal1? board mark)
      (diagonal2? board mark)
      (vertical? board mark)))
