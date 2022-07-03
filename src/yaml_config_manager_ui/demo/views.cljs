(ns yaml-config-manager-ui.demo.views
  (:require
   [clojure.pprint :refer [pprint]]
   [clojure.string :refer [join]]
   [re-frame.core :as rf]
   [reagent.core :as r]
   [yaml-config-manager-ui.views :refer [router vertical-menu]]))

(defn navbar
  "Displays a blessed js box with a vertical-menu used for navigation.
  User can use j/k or up/down to navigate items and either enter or l to view
  a page. Dispatches re-frame :update to set :router/view in app db.
  Returns a hiccup :box vector."
  [_]
  [:box#home
   {:bottom 0
    :left   0
    :width  "70%"
    :height "40%"
    :style  {:border {:fg :cyan}}
    :border {:type :line}
    :label  " Current Config "}
   [vertical-menu {:options {:home "Intro"                  ; TODO: This is where I'd want to replace the options
                             :about "About"
                             :resources "Resources"
                             :credits "Credits"}
                   :bg :magenta
                   :fg :black
                   :on-select #(rf/dispatch [:update {:router/view %}])}]])

(defn help
  "Display welcome message and general usage info to user.
  Returns hiccup :box element."
  [_]
  [:box#home
   {:bottom 0
    :right 0
    :width "30%"
    :height "40%"
    :style {:border {:fg :magenta}}
    :border {:type :line}
    :label " Controls "}
   [:box#content
    {:top 0
     :left 0
     :right 0}
    [:box#keys
     {:top 0
      :left 0
      :right 0
      :align :left
      :content "Usage:\n\n  - j/k or up/down to select a config value\n  - enter or l to make a selection"}]]])

(defn yaml-display-left
  "Displays the yaml file.
  Returns hiccup :box vector."
  [_]
  [:box#left-yaml
   {:top 0
    :left 0
    :width "50%"
    :height "60%"
    :style {:border {:fg :red}}
    :border {:type :line}
    :label (str " " "<INSERT ENV HERE>" ":" "<INSERT FILE NAME HERE>" " ")}
   [:box#content
    {:top 1
     :left 1
     :right 1
     :bottom 1}
    [:text (join "\n  - "
                 ["Learn more about the technology behind this powerful ClojureScript template:\n"
                  "https://clojurescript.org/"
                  "https://github.com/chjj/blessed"
                  "https://github.com/Yomguithereal/react-blessed"
                  "https://reagent-project.github.io/"
                  "https://shadow-cljs.org/"
                  "https://figwheel.org/"
                  "https://github.com/bhauman/lein-figwheel"])]]])
(defn yaml-display-right
  "Displays the yaml file.
  Returns hiccup :box vector."
  [_]
  [:box#right-yaml
   {:top 0
    :right 0
    :width "50%"
    :height "60%"
    :style {:border {:fg :red}}
    :border {:type :line}
    :label (str " " "<INSERT ENV HERE>" ":" "<INSERT FILE NAME HERE>" " ")}
   [:box#content
    {:top 1
     :left 1
     :right 1
     :bottom 0}
    [:text (join "\n  - "
                 ["Learn more about the technology behind this powerful ClojureScript template:\n"
                  "https://clojurescript.org/"
                  "https://github.com/chjj/blessed"
                  "https://github.com/Yomguithereal/react-blessed"
                  "https://reagent-project.github.io/"
                  "https://shadow-cljs.org/"
                  "https://figwheel.org/"
                  "https://github.com/bhauman/lein-figwheel"])]]])
(defn yaml-viewer
  "Displays two files side-by-side"
  [_]
  [:box#Files [yaml-display-left] [yaml-display-right]])

(defn yaml-ui
  "Main UI wrapper.

  Takes a hash-map and a hiccup child vector:

  hash-map:
  :view keyword - Current view keyword that maps to one of the views below.

  child:
  Typically something like a hiccup [:box ...] vector

  Returns hiccup :box vector."
  [{:keys [view]}]
  [:box#base {:left   0
              :right  0
              :width  "100%"
              :height "100%"}
   [router {:views {
                    :viewer yaml-viewer
                    }
            :view view}]
   [navbar]
   [help]])