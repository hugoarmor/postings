(defproject postings "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0",
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [ring "1.12.1"]
                 [ring/ring-jetty-adapter "1.12.1"]
                 [compojure "1.7.0"]
                 [com.github.seancorfield/next.jdbc "1.3.909"]
                 [org.postgresql/postgresql "42.6.0"]
                 [cheshire "5.12.0"]
                 [migratus "1.5.7"]
                 [cheshire "5.12.0"]
                 [org.slf4j/slf4j-simple "2.0.9"]
                 [org.clojure/tools.logging "1.2.4"]
                 [org.clojure/core.match "1.0.1"]]
  :plugins [[migratus-lein "0.7.3"]]
  :migratus {:store :database,
             :db {:dbname "todo_db",
                  :dbtype "postgresql",
                  :user "clojure_user",
                  :password "clojure_pass",
                  :host "localhost",
                  :port 5432}}
  :repl-options {:init-ns postings.core}
  :main postings.core)
