(ns postings.controllers.posts
  (:require [cheshire.core :as json]
            [postings.repositories.posts :as repo]
            [clojure.core.match :refer [match]]))

(def json-headers {"Content-Type" "application/json"})

(defn index
  [_ {:keys [ds]}]
  {:status 200,
   :headers {"Content-Type" "application/json"},
   :body (json/generate-string (repo/all ds))})

(defn create
  [{body :json-params} {:keys [ds]}]
  (match (repo/create ds body)
    {:ok _} {:status 201,
             :headers {"Content-Type" "application/json"},
             :body (json/generate-string {:message "Todo created"})}
    {:error err} {:status 400,
                  :headers json-headers,
                  :body (json/generate-string {:error err})}))

(defn change
  [{:keys [json-params params]} {:keys [ds]}]
  (let [id (some-> (:id params)
                   Integer/parseInt)]
    (when-not id (throw (ex-info "Invalid ID" {})))
    (when (empty? json-params)
      (throw (ex-info "No fields provided to update" {})))
    (match (repo/change ds id json-params)
      {:ok _} {:status 201,
               :headers json-headers,
               :body (json/generate-string {:message "Todo changed"})}
      {:error err} {:status 400,
                    :headers json-headers,
                    :body (json/generate-string {:error err})})))

(defn destroy
  [{:keys [params]} {:keys [ds]}]
  (let [id (some-> (:id params)
                   Integer/parseInt)]
    (when-not id (throw (ex-info "Invalid ID" {})))
    (match (repo/delete ds id)
      {:ok _} {:status 201,
               :headers json-headers,
               :body (json/generate-string {:message "Todo deleted"})}
      {:error err} {:status 400,
                    :headers json-headers,
                    :body (json/generate-string {:error err})})))
