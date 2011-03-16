(defproject slick-test "1.0.0-SNAPSHOT"
  :description         "FIXME: write"
  :dependencies        [[org.clojure/clojure "1.2.0"]
                        [org.clojure/clojure-contrib "1.2.0"]
                        [slick "1.0.0"]
                        [org.lwjgl/lwjgl "2.7.1"]
                        [org.lwjgl/lwjgl-util "2.7.1"]]
  :dev-dependencies    [[swank-clojure "1.2.1"]
                        [native-deps "1.0.5"]]
  :main                slick-test.core
  :repositories     {"snapshots" "http://maven.prettyrandom.net/"
                     "releases"  "http://maven.prettyrandom.net/"}
  :native-dependencies [[org.lwjgl/lwjgl-native-platform "2.7.1"]])

