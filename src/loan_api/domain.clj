(ns loan-api.domain)

(defn- valid-number? [n]
  (and (number? n) (not (neg? n))))

(defn- valid-months? [m]
  (and (integer? m) (pos? m)))

(defn- validate-input!
  [{:keys [amount rate months] :as input}]
  (cond
    (nil? amount)
    (throw (ex-info "Amount is required" {:type :validation-error :field :amount}))

    (nil? rate)
    (throw (ex-info "Rate is required" {:type :validation-error :field :rate}))

    (nil? months)
    (throw (ex-info "Months is required" {:type :validation-error :field :months}))

    (not (valid-number? amount))
    (throw (ex-info "Amount must be a positive number"
                    {:type :validation-error :field :amount}))

    (not (valid-number? rate))
    (throw (ex-info "Rate must be a non-negative number"
                    {:type :validation-error :field :rate}))

    (not (valid-months? months))
    (throw (ex-info "Months must be a positive integer"
                    {:type :validation-error :field :months}))

    :else true))

(defn calculate-loan
  [input]
  (validate-input! input)
  (let [{:keys [amount rate months]} input
        total (* amount (Math/pow (+ 1 rate) months))
        installment (/ total months)]
    {:total (double total)
     :installment (double installment)}))
