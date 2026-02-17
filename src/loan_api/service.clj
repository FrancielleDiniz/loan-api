(ns loan-api.service
  (:require [loan-api.domain :as domain]))

(defn simulate-loan
  [input]
  (domain/calculate-loan input))
