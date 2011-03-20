(ns slick-test.core
  (:gen-class)
  (:use [clojure.contrib.generic.math-functions :only [sin cos]])
  (:import [org.newdawn.slick
	    AppGameContainer BasicGame GameContainer Graphics Image Input SlickException]))

(def land (atom nil))
(defn create-image [storage file]
  (Image. file))

(defn init-game [gc])
(defn update-game [gc delta])
(defn render-game [gc g])

(def simple-game
     (proxy [BasicGame]
	 ["Slick2DPath2Glory - SimpleGame"]
       (init [gc]
	     (init-game gc))
       (update [gc delta]
	       (update-game gc delta))
       (render [gc g]
	       (render-game gc g))))

(def app-game-container (AppGameContainer. simple-game))

