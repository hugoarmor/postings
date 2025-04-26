(ns postings.core
  (:require 
            [clojure.tools.logging :as log]
            [compojure.core :refer [defroutes]]
            [compojure.route :as route]
            [next.jdbc :as jdbc]
            [postings.middleware :refer [wrap-json-body]]
            [postings.routes :as routes]
            [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

(def PORT 3000)

;; 🗃️ DB config
(def db-spec
  {:dbtype "postgresql",
   :dbname "todo_db",
   :host "localhost",
   :user "clojure_user",
   :password "clojure_pass"})

(def ds (jdbc/get-datasource db-spec))
(def ctx {:ds ds})

;; 🧾 Routes
(defroutes app-routes
  (routes/posts-routes ctx)
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      wrap-json-body))

;; 🚀 Start server
(defn -main
  []
  (run-jetty app {:port PORT, :join? false})
  (log/info (format "🚀 Server started at %d" PORT)))
