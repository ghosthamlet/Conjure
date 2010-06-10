(ns test.binding.test-base
  (:use clojure.contrib.test-is
        conjure.binding.base)
  (:require [conjure.model.database :as database]
            [conjure.server.request :as request]
            [conjure.util.session-utils :as session-utils]
            [destroyers.binding-destroyer :as binding-destroyer]
            [destroyers.view-destroyer :as view-destroyer]
            [generators.binding-generator :as binding-generator]))

(def controller-name "test")
(def action-name "show")

(defn setup-all [function]
  (binding-generator/generate-binding-file 
    { :controller controller-name, :action action-name, :silent true })
  (function)
  (binding-destroyer/destroy-all-dependencies controller-name action-name true))
        
(use-fixtures :once setup-all)

(deftest test-render-view
  (request/with-controller-action controller-name action-name
    (let [view (render-view)]
      (is (not (nil? view)))
      (is (map? view)))))