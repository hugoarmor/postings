(ns postings.repositories.posts
  (:require [next.jdbc :as jdbc]
            [next.jdbc.result-set :as rs]
            [clojure.string :as string]
            [postings.database.lib :refer [safe-query]]))

(defn all
  "Returns all posts"
  [ds]
  (safe-query #(jdbc/execute! ds
                              ["SELECT * FROM posts"]
                              {:builder-fn rs/as-unqualified-lower-maps})))

(defn create
  "Creates a new post"
  [ds {:keys [title description]}]
  (safe-query #(jdbc/execute!
                ds
                ["INSERT INTO posts (title, description) VALUES (?, ?)" title
                 description])))

(defn change
  "Updates fields of a post by ID. Only provided fields will be updated."
  [ds id fields]
  (let [columns (keys fields)
        values (vals fields)
        ;; Dynamically build "col1 = ?, col2 = ?"
        set-clause (->> columns
                        (map (fn [col] (str (name col) " = ?")))
                        (string/join ", "))
        sql (str "UPDATE posts SET " set-clause " WHERE id = ?")]
    (safe-query #(jdbc/execute! ds (into [sql] (conj (vec values) id))))))

(defn delete
  "Deletes a post by ID"
  [ds id]
  (safe-query #(jdbc/execute! ds ["DELETE FROM posts WHERE id = ?" id])))
