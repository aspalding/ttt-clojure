(ns ttt-clojure.ai
  (:require [ttt-clojure.board  :as board]
             [ttt-clojure.state :as state]))

  (defn score [board opponent]
    (if (state/winner? board opponent)
      -10
      (if (state/winner? board (state/opposing? opponent))
        10
        0)))
