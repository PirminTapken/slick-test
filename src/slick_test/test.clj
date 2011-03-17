(ns slick-test.test
  ( :use [slick-test.core :only [SimpleGame]])
  (:import org.newdawn.slick.AppGameContainer))

(def game-container (atom nil))

(defn thread-me []
  (reset! game-container (AppGameContainer. SimpleGame))
  (.setDisplayMode @game-container 800 600 false)
  (.start @game-container)
  nil)

(defn test-me []
  (println "starting thread")
  (.start (Thread. thread-me))
  (println "done"))

(defn stop-me []
  (.destroy @game-container))