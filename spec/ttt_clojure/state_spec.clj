(ns ttt-clojure.state-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.board :as board]
            [ttt-clojure.state :as state]))

  (describe "full?"
    (it "should return true if there are no more open positions"
      (let [board (repeat 9 "x")]
      (should (state/full? board)))))

  (describe "opposing?"
    (it "should return the mark opposite of the current mark"
      (def player "O" )
      (should= "X" (state/opposing? player))))

  (describe "horizontal?"
    (it "should return true if there are three marks in a row horiontally"
      (let [board (board/create-board 3)
          board (assoc board 0 "x" 1 "x" 2 "x")]
          (should (state/horizontal? board "x")))))
