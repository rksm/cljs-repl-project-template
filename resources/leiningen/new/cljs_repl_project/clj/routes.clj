(ns {{name}}.routes
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]
            [compojure.core :as comp]))

(comp/defroutes main-routes
  (route/resources "/")
  (route/not-found "Page not found"))

(def app
  (-> (handler/site main-routes)))
