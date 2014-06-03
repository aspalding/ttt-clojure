(ns ttt-clojure.board-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.board :as board]))

(describe "create-board"
  (it "should initialize board"
    (let [board (board/create-board 3)]
    (should= 9 (count board)))))

(describe "place-piece"
  (it "should place piece at location provided"
    (let [board (board/create-board 3)
          board (board/place-piece board "x" 0)]
    (should= "x" (nth board 0))))
  (it "should only accept a valid move"
    (let [board (board/create-board 3)]
    (should= "invalid" (board/place-piece board "x" -1)))))

(describe "valid?"
  (it "should only accept a blank space"
    (let [board (board/create-board 3)
        board (board/place-piece board "x" 0)]
    (should-not (board/valid? board 0))))

  (it "should not accept out of bounds move"
    (let [board (board/create-board 3)]
    (should-not (board/valid? board 9)))))

(describe "possible-moves"
  (it "should return all positions that are not currently occupied"
    (let [board (board/create-board 3)
        board (assoc board 0 "x" 1 "x" 2 "x" 3 "x")]
    (should= (range 4 9) (board/possible-moves board)))))
