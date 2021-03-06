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
      (def player "o" )
      (should= "x" (state/opposing? player))))

  (describe "horizontal?"
    (it "should return true if there are three marks in a row horiontally"
      (let [board (board/create-board 3)
          board (assoc board 0 "x" 1 "x" 2 "x")]
          (should (state/horizontal? board "x")))))

  (describe "diagonal1?"
    (it "should return true if there are three marks in a row diagonally"
      (let [board (board/create-board 3)
        board (assoc board 0 "x" 4 "x" 8 "x")]
        (should (state/diagonal1? board "x")))))

  (describe "diagonal2?"
    (it "should return true if there are three marks in a row diagonally"
      (let [board (board/create-board 3)
        board (assoc board 2 "x" 4 "x" 6 "x")]
        (should (state/diagonal2? board "x")))))

  (describe "vertical?"
   (it "shoud return true if there are three marks in a row vertically"
     (let [board (board/create-board 3)
       board (assoc board 0 "x" 3 "x" 6 "x")]
       (should (state/vertical? board "x")))))

  (describe "winner?"
    (it "should return true if there is any winning position (vert)"
      (let [board (board/create-board 3)
        board (assoc board 1 "x" 4 "x" 7 "x")]
        (should (state/winner? board "x"))))
    (it "should return true if there is any winning position (dia)"
      (let [board (board/create-board 3)
        board (assoc board 2 "x" 4 "x" 6 "x")]
        (should (state/winner? board "x"))))
    (it "should return true if there is any winning position (dia)"
      (let [board (board/create-board 3)
        board (assoc board 0 "x" 3 "x" 6 "x")]
        (should (state/winner? board "x"))))
    (it "should return true if there is any winning position (horiz)"
      (let [board (board/create-board 3)
        board (assoc board 3 "x" 4 "x" 5 "x")]
        (should (state/winner? board "x"))))
    (it "should not if the board is empty"
      (let [board (board/create-board 3)]
        (should-not (state/winner? board "x")))))

  (describe "terminal?"
    (it "should return true if full."
      (let [board (repeat 9 "x")]
      (should (state/terminal? board))))
    (it "should return true if game has winner."
      (let [board (board/create-board 3)
        board (assoc board 6 "x" 7 "x" 8 "x")]
      (should (state/terminal? board))))
    (it "should return true if game has winner."
      (let [board (board/create-board 3)
        board (assoc board 6 "o" 7 "o" 8 "o")]
      (should (state/terminal? board)))))
