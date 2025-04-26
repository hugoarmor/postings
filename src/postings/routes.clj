(ns postings.routes
  (:require [compojure.core :refer [GET POST PUT DELETE routes context]]
            [postings.controllers.posts :as controller]))

(defn posts-routes
  [ctx]
  (context "/posts"
    []
    (routes (GET "/" req (controller/index req ctx))
            (POST "/" req (controller/create req ctx))
            (PUT "/:id" req (controller/change req ctx))
            (DELETE "/:id" req (controller/destroy req ctx)))))
