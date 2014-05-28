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
      (should= "x is the winner!"
      (with-out-str (out/winning-mark "x")))))
