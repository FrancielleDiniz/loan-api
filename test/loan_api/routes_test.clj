(ns loan-api.routes-test
  (:require
   [clojure.test :refer :all]
   [ring.mock.request :as mock]
   [cheshire.core :as json]
   [loan-api.routes :refer [app]]))

(deftest simulate-endpoint-success
  (testing "POST /simulate returns 200 with valid input"
    (let [request (-> (mock/request :post "/simulate")
                      (mock/content-type "application/json")
                      (mock/body (json/generate-string
                                  {:amount 10000
                                   :rate 0.02
                                   :months 12})))
          response (app request)
          body (json/parse-string (:body response) true)]

      (is (= 200 (:status response)))
      (is (contains? body :total))
      (is (contains? body :installment)))))


(deftest simulate-endpoint-invalid-input
  (testing "POST /simulate returns 400 for invalid input"
    (let [request (-> (mock/request :post "/simulate")
                      (mock/content-type "application/json")
                      (mock/body (json/generate-string
                                  {:amount -10
                                   :rate 0.02
                                   :months 12})))
          response (app request)]

      (is (= 400 (:status response))))))
