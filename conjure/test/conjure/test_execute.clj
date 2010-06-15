(ns conjure.test-execute
  (:use clojure.contrib.test-is
        conjure.execute))

(deftest test-run-script
  (run-script "noop" [])
  (run-script "fail" [])
  (run-script "invalid" []))

(deftest test--main
  (-main "noop"))