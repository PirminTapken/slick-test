(ns slick-test.core
  (:gen-class)
  (:use [clojure.contrib.generic.math-functions :only [sin cos]])
  (:import [org.newdawn.slick
	    AppGameContainer BasicGame GameContainer Graphics Image Input SlickException]))

(def land (atom nil))
;plane uses refs if it is necessary to change x and y at the same time
(def plane {:image (ref nil)
	    :x (ref 400)
	    :y (ref 300)
	    :scale (ref 1)})

(defn create-image [storage file]
  "Function to create an image. First parameter is agent/ref/whatever"
  (Image. file))


(defn init-game [gc]
  "Inits the game. Is called by the game object."
  (reset! land (Image. "land.jpg"))
  (dosync (alter (:image plane) create-image "plane.png")))

(defn update-game [gc delta]
  "For event handling!?")

(defn render-game [gc g]
  "Renders the game."
  (dosync
   (let [bg @land
	 ; plane should be optimzed...
	 plane {:image @(:image plane)
		:x @(:x plane)
		:y @(:y plane)
		:scale @(:scale plane)}]
     (.draw bg)
     (.draw (:image plane) (:x plane) (:y plane)))))

; The game as an object.
(def simple-game
     (proxy [BasicGame]
	 ["Slick2DPath2Glory - SimpleGame"]
       (init [gc]
	     (init-game gc))
       (update [gc delta]
	       (update-game gc delta))
       (render [gc g]
	       (render-game gc g))))

; The container controlling the game
(def app-game-container (doto (AppGameContainer. simple-game) (.setDisplayMode 800 600 false)))

;This is the agent running the game
(def running-game (agent nil))

(defn start-game []
  "starts the game"
  (send-off running-game (fn [_] (.start app-game-container))))

(defn exit-game []
  "exits the game"
  (.exit app-game-container))