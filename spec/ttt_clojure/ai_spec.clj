(ns ttt-clojure.ai-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.board :as board]
            [ttt-clojure.state :as state]
            [ttt-clojure.ai :as ai]))

  (describe "score"
    (it "should score a losing board -10"
      (let [board (board/create-board 3)
          board (assoc board 0 "x" 1 "x" 2 "x")]
          (should= -10 (ai/score board))))
    (it "should score a winning board 10"
      (let [board (board/create-board 3)
          board (assoc board 0 "o" 1 "o" 2 "o")]
          (should= 10 (ai/score board))))
    (it "should score 0 for a draw"
      (let [board (board/create-board 3)
          board (assoc board 0 "x" 1 "o" 2 "x"
                             3 "o" 4 "x" 5 "o"
                             6 "o" 7 "x" 8 "o")]
      (should= 0 (ai/score board)))))

  (describe "minimax"
    (it "should return -10 for opponent"
      (let [board (board/create-board 3)
        board (assoc board 0 "x" 1 "x" 2 "x")]
        (should= -10 (ai/score-move board "o" true))))
    (it "should return 10 for self"
      (let [board (board/create-board 3)
        board (assoc board 0 "o" 1 "o" 2 "o")]
        (should= 10 (ai/score-move board "o" true))))
    (it "should score 0 for a draw"
      (let [board (board/create-board 3)
          board (assoc board 0 "x" 1 "o" 2 "x"
                             3 "o" 4 "x" 5 "o"
                             6 "o" 7 "x" 8 "o")]
      (should= 0 (ai/score-move board "o" true))))
    (it "should score each move 0 on blank board"
      (let [board (board/create-board 3)]
        (should= {8 0 7 0 6 0 5 0 4 0 3 0 2 0 1 0 0 0}
          (ai/score-moves board "o"))))
    (it "should score 10 for winning position"
      (let [board (board/create-board 3)
          board (assoc board 0 "o" 1 "o" 2 "-"
                             3 "-" 4 "x" 5 "x"
                             6 "-" 7 "-" 8 "-")]
        (should= 10 (get (ai/score-moves board "o") 2))))
    (it "should pick the best move"
      (let [board (board/create-board 3)
        board (assoc board 0 "x" 1 "o" 2 "x"
                           3 "o" 4 "x" 5 "o"
                           6 "o" 7 "x" 8 "-")]
        (should= 8 (ai/best-move board "x"))))
    (it "should block move if chace of losing"
      (let [board (board/create-board 3)
        board (assoc board 0 "-" 1 "-" 2 "-"
                           3 "-" 4 "x" 5 "-"
                           6 "x" 7 "o" 8 "o")]
        (should= 2 (ai/best-move board "o")))))
