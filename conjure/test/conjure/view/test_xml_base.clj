(ns conjure.view.test-xml-base
  (:use clojure.contrib.test-is
        conjure.view.xml-base))

(def-xml []
  [:test])

(deftest test-defxml
  (is (= { :status 200, :headers { "Content-Type" "text/xml" }, :body "<test/>" } (render-view))))