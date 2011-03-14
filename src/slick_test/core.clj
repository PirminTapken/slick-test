(ns slick-test.core
  (:import [org.newdawn.slick AppGameContainer BasicGame GameContainer Graphics Image SlickException])
  (:gen-class))

(defn make-game
  [window-name image]
  (let [img (atom nil)]
    (proxy [BasicGame] [window-name]
      (init [gc]
        (swap! img (fn [img] (Image. (str "resources/" image)))))
      (update [gc delta]
        (.draw @img 100 100))
      (render [gc graphics]))))

(defn -main
  []
  (doto (AppGameContainer. (make-game "lol pirm" "pirmin.png"))
    (.setDisplayMode 800 600 false)
    (.start)))

