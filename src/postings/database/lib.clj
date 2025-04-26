(ns postings.database.lib)


(defn safe-query [f]
  (try
    {:ok (f)}
    (catch org.postgresql.util.PSQLException e
      {:error (.getMessage e)})
    (catch Exception e
      {:error (.getMessage e)})))
