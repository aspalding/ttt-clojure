(ns ttt-clojure.ai-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.board :as board]
            [ttt-clojure.state :as state]
            [ttt-clojure.ai :as ai]))

  (describe "score"
    (it "should score a losing board -10"
      (let [board (board/create-board 3)
          board (assoc board 0 "x" 1 "x" 2 "x")]
          (should= -10 (ai/score board "x"))))
    (it "should score a winning board 10"
      (let [board (board/create-board 3)
          board (assoc board 0 "x" 1 "x" 2 "x")]
          (should= 10 (ai/score board "o"))))
    (it "should score 0 for a draw"
      (let [board (board/create-board 3)
          board (assoc board 0 "x" 1 "o" 2 "x"
                             3 "o" 4 "x" 5 "o"
                             6 "o" 7 "x" 8 "o")]
      (should= 0 (ai/score board "o")))))


(comment
  (describe "not smart"
    (it "should return an Integer"
    (let [board (board/create-board 3)
        board (assoc board 0 "x" 1 "x" 2 "x")]
      (should-be-a Integer (ai/pick-move (board))))))



  (describe "minimax"
    (it "should stop branching if winner"
    (let [board (board/create-board 3)
        board (assoc board 0 "x" 1 "x" 2 "x")]
        (should= 10 (ai/minimax board 2 "o")))))
)
