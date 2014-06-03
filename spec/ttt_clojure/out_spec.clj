(ns ttt-clojure.out-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.board :as board]
            [ttt-clojure.state :as state]
            [ttt-clojure.out :as out]))

  (describe "print-board"
    (it "should output the board"
    (let [board (board/create-board 3)]
      (should= (str "\n - | - | - \n - | - | - \n - | - | - \n\n")
      (with-out-str (out/show-board board))))))

  (describe "prompt"
    (it "should prompt user for move 0-8"
    (let [board (board/create-board 3)]
          (with-in-str "0"
            (let [board (out/prompt board "x")]
            (should= "x" (nth board 0)))))))

  (describe "winning message"
    (it "should let the winner know they have won"
      (should= "x is the winner!\n"
      (with-out-str (out/winning-mark "x")))))

  (describe "tie message"
    (it "should let the participants know they've tied"
      (should= "It's a tie!\n"
      (with-out-str (out/tie)))))

  (describe "closing messages"
    (it "call a tie message"
      (let [board (board/create-board 3)
          board (assoc board 0 "x" 1 "o" 2 "x"
                             3 "o" 4 "x" 5 "o"
                             6 "o" 7 "x" 8 "o")]
        (should= "It's a tie!\n"
        (with-out-str (out/over board "x")))))
      (it "call a winner message"
        (let [board (board/create-board 3)
            board (assoc board 0 "x" 1 "o" 2 "x"
                               3 "o" 4 "x" 5 "o"
                               6 "o" 7 "x" 8 "x")]
          (should= "x is the winner!\n"
          (with-out-str (out/over board "x")))))
      (it "call a winner message"
        (let [board (board/create-board 3)
            board (assoc board 0 "o" 1 "o" 2 "x"
                               3 "o" 4 "x" 5 "o"
                               6 "o" 7 "x" 8 "x")]
          (should= "o is the winner!\n"
          (with-out-str (out/over board "o"))))))
