(ns ttt-clojure.out
  (:require [ttt-clojure.board  :as board]
             [ttt-clojure.state :as state]
             [ttt-clojure.ai :as ai]))

  (defn show-board [board]
    (print
      (apply format
        "\n %s | %s | %s \n %s | %s | %s \n %s | %s | %s \n\n"
          (flatten (partition 3 board)))))

  (defn prompt [board mark]
    (println "Enter valid position (0..8)")
    (let [input (Integer/parseInt (read-line))]
      (if (board/valid? board input)
        (board/place-piece board mark input)
        (recur board mark))))

  (defn winning-mark [mark]
    (print (str mark " is the winner!\n")))

  (defn tie []
    (print (str "It's a tie!\n")))

  (defn over [board mark]
    (if(state/winner? board mark)
      (winning-mark mark)
      (tie)))

  (defn who [game player]
    (if (= "x" player)
      (prompt game player)
      (ai/pick-move game)))

  (defn run-loop []
    (def board (board/create-board 3))
    (def player "x")
    (loop [game board player player]
      (show-board game)
      (if (state/terminal? game)
        (over game (state/opposing? player))
      (recur (who game player) (state/opposing? player)))))

  (defn -main []
    (println "testing.")
    (run-loop))
