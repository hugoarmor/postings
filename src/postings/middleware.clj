(ns postings.middleware
  (:require [cheshire.core :as json]
            [clojure.java.io :as io]))

(defn wrap-json-body
  [handler]
  (fn [request]
    (let [content-type (get-in request [:headers "content-type"])]
      (if (and content-type (.startsWith content-type "application/json"))
        (let [body (-> request
                       :body
                       io/reader
                       (json/parse-stream true))]
          (handler (assoc request :json-params body)))
        (handler request)))))
