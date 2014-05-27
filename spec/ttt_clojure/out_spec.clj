(ns ttt-clojure.out-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.board :as board]
            [ttt-clojure.state :as state]
            [ttt-clojure.out :as out]))

  (describe "print-board"
    (it "should output something"
    (let [board (board/create-board 3)]
    (should= (str "\n - | - | - \n - | - | - \n - | - | - \n\n")
    (with-out-str (out/show-board board))))))

  (describe "prompt"
    (it "should prompt user for move 0-8"
    (let [board (board/create-board 3)]
    (should= ["x" "-" "-" "-" "-" "-" "-" "-" "-"] board
      (with-in-str "0")
        (out/prompt board "x")))))
