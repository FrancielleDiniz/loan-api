(ns loan-api.core
  (:require [loan-api.http :as http])
  (:gen-class))

(defn -main [& _]
  (http/start))

