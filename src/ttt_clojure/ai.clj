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

  (defn minimax [board depth mark]
    (let [possible (board/possible-moves board)]
    (if (or (state/terminal? board) (= depth 0))
      (score board mark))
    ))
