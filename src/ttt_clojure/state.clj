(ns ttt-clojure.state
  (:require [ttt-clojure.board  :as board]))

  (defn full? [board]
    (= (board/possible-moves board) () ))

  (defn opposing? [player]
    (if (= player "X")
        "O"
        (if (= player "O")
            "X")))

  (defn horizontal? [board mark]
    (loop [i 0]
    (when (< i 3)
    (if (= (nth (partition 3 board) i) (repeat 3 mark))
      true
    (recur (inc i))))))
