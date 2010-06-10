(ns views.templates.delete-warning
  (:use conjure.view.base)
  (:require [clj-html.core :as html]
            [clj-html.helpers :as helpers]
            [views.templates.record-view :as record-view]))

(defview [model-name table-metadata record]
  [:div { :class "article" }
    [:h2 (str "Deleting " (or (helpers/h (:name record)) (:id record)))]
    [:p "Are you sure you want to delete this record?"]
    (record-view/render-body table-metadata record)
    (button-to "Delete" { :action "delete", :controller model-name, :params { :id record } })
    "&nbsp;"
    (link-to "Cancel" { :action "list-records", :controller model-name })])