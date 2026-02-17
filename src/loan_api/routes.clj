(ns loan-api.routes
  (:require
   [reitit.ring :as ring]
   [cheshire.core :as json]
   [loan-api.service :as service]))

(defn parse-body [request]
  (-> request :body slurp (json/parse-string true)))

(defn wrap-exception-handling [handler]
  (fn [request]
    (try
      (handler request)
      (catch clojure.lang.ExceptionInfo e
        (let [{:keys [type explain]} (ex-data e)]
          (if (= type :validation-error)
            {:status 400
             :headers {"Content-Type" "application/json"}
             :body (json/generate-string
                    {:error "Invalid input"
                     :details explain})}
            (throw e)))))))

(def routes
  (ring/router
   [["/simulate"
     {:post
      (fn [request]
        (let [input (parse-body request)
              result (service/simulate-loan input)]
          {:status 200
           :headers {"Content-Type" "application/json"}
           :body (json/generate-string result)}))}]]))

(def app
  (wrap-exception-handling
   (ring/ring-handler routes)))
