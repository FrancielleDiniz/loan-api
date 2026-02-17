(ns loan-api.http
  (:require
   [ring.adapter.jetty :refer [run-jetty]]
   [loan-api.routes :refer [app]]))

(defn start []
  (run-jetty app {:port 3000 :join? false}))
