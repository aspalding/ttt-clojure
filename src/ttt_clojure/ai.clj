(ns ttt-clojure.ai
  (:require [ttt-clojure.board  :as board]
             [ttt-clojure.state :as state]))

  (defn score [board]
    (if (state/winner? board "o")
      10
      (if (state/winner? board (state/opposing? "o"))
        -10
        0)))

  (defn rand-move [board]
    (let [move (rand-int 9)]
      (if (board/valid? board move)
        move
        (recur board))))

  (defn score-move [board mark computer]
    (if (state/terminal? board)
      (score board)
      (reduce (if computer min max)
        (map
          #(score-move (assoc board %1 (state/opposing? mark)) (state/opposing? mark) (not computer))
          (board/possible-moves board)))))

  (defn score-moves [board mark]
    (reduce
      #(assoc %1 %2 (score-move (assoc board %2 mark) mark true))
      (hash-map) (board/possible-moves board)))

  (defn best-move [board mark]
    (first (flatten (sort-by val > (score-moves board mark)))))

  (defn pick-move [board]
    (board/place-piece board "o" (best-move board "o")))
