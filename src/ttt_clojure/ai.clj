(ns ttt-clojure.ai
  (:require [ttt-clojure.board  :as board]
             [ttt-clojure.state :as state]))

  (defn score [board opponent]
    (if (state/winner? board opponent)
      -10
      (if (state/winner? board (state/opposing? opponent))
        10
        0)))

  (defn rand-move [board]
    (let [move (rand-int 9)]
      (if (board/valid? board move)
        move
        (recur board))))

  (defn pick-move [board]
    (board/place-piece board "o" (rand-move board)))

(comment
  (defn minimax [board depth mark]
    (def possible (board/possible-moves board))
    (loop [i 0]

    (when (< i (count possible))
    (recur (board/place-piece board (nth possible i) mark)
            (dec depth)
            (state/opposing? mark))))
          (if (or (state/terminal? board) (= depth 0))
            (score board mark))))
