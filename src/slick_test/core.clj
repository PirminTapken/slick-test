(ns slick-test.core
  (:gen-class)
  (:import [org.newdawn.slick AppGameContainer BasicGame GameContainer Graphics Image Input SlickException]))
(defn to-radians [degrees] (Math/toRadians degrees))
(defn sinus-of [rad] (Math/sin rad))
(defn cosin-of [rad] (Math/cos rad))
(def SimpleGame
  (let
    [land (atom nil)
     plane (atom nil)
     plane-data (atom [400 300])]
    (proxy
      [BasicGame]
      ["Slick2DPath2Glory - SimpleGame"]
      (init [gc]
        (let [img (fn [x file] (Image. file))]
          (swap! land
                 img "land.jpg")
          (swap! plane
                 img "plane.png")))
      (update [gc delta]
        (let [input (.getInput gc)
              key-down? (fn [key] (.isKeyDown input key))]
            (if (key-down? Input/KEY_A)
              (.rotate @plane (* -0.2 delta)))
            (if (key-down? Input/KEY_D)
              (.rotate @plane (* 0.2 delta)))
            (if (key-down? Input/KEY_W)
              (swap! plane-data
                     (fn [pd hip rotation]
                       [(+ (* hip (sinus-of rotation)) (first pd))
                        (- (* hip (cosin-of rotation)) (last pd))])
                     (* 0.4 delta)
                     (to-radians (.getRotation @plane))))))
      (render [gc g]
        (.draw @land 0 0)
        (apply #(.drawCentered %1 %2 %3) @plane @plane-data)))))
(defn -main []
  (doto (AppGameContainer. SimpleGame)
    (.setDisplayMode 800 600 false)
    (.start)))
