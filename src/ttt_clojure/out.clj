(ns ttt-clojure.out
  (:require [ttt-clojure.board  :as board]
             [ttt-clojure.state :as state]))

  (defn show-board [board]
    (print
      (apply format
        "\n %s | %s | %s \n %s | %s | %s \n %s | %s | %s \n\n"
          (flatten (partition 3 board)))))

  (defn prompt [board mark]
    (board/place-piece board mark (Integer/parseInt (read-line))))

  (defn -main []
    (println "testing."))
