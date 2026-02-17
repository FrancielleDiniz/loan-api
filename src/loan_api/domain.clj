(ns loan-api.domain)

(defn calculate-loan
  [{:keys [amount rate months]}]
  (let [total (* amount (Math/pow (+ 1 rate) months))
        installment (/ total months)]
    {:total (double total)
     :installment (double installment)}))