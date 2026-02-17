(ns loan-api.domain
  (:require [clojure.spec.alpha :as s]))

(s/def ::amount (s/and number? pos?))
(s/def ::rate (s/and number? (complement neg?)))
(s/def ::months (s/and int? pos?))

(s/def ::loan-input
  (s/keys :req-un [::amount ::rate ::months]))

(defn- validate-input!
  [input]
  (when-not (s/valid? ::loan-input input)
    (throw (ex-info "Invalid loan input"
                    {:type :validation-error
                     :explain (s/explain-data ::loan-input input)}))))

(defn calculate-loan
  [input]
  (validate-input! input)
  (let [{:keys [amount rate months]} input
        total (* amount (Math/pow (+ 1 rate) months))
        installment (/ total months)]
    {:total (double total)
     :installment (double installment)}))
