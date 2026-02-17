(ns loan-api.domain-test
  (:require
   [clojure.test :refer :all]
   [loan-api.domain :as domain]))

(deftest calculate-loan-test
  (testing "calculates total and installment correctly"
    (let [input {:amount 10000
                 :rate 0.02
                 :months 12}
          result (domain/calculate-loan input)]

      ;; basic structure validation
      (is (contains? result :total))
      (is (contains? result :installment))

      ;; business rule validations
      (is (> (:total result) 10000))
      (is (= (/ (:total result) 12)
             (:installment result))))))

(deftest calculate-loan-invalid-input-test
  (testing "throws validation error for invalid amount"
    (is (thrown-with-msg?
         clojure.lang.ExceptionInfo
         #"Amount must be a positive number"
         (domain/calculate-loan {:amount -10 :rate 0.02 :months 12}))))

  (testing "throws validation error for invalid months"
    (is (thrown-with-msg?
         clojure.lang.ExceptionInfo
         #"Months must be a positive integer"
         (domain/calculate-loan {:amount 1000 :rate 0.02 :months 0})))))

