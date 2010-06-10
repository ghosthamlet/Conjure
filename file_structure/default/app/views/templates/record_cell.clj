(ns views.templates.record-cell
  (:use conjure.view.base)
  (:require [clj-html.core :as html]
            [clj-html.helpers :as helpers]
            [conjure.util.string-utils :as conjure-str-utils]
            [conjure.view.util :as view-utils]))

(defview [model-name record record-key]
  (let [record-id (:id record)]
    (if (= :id record-key)
      [:td 
        (ajax-link-to (helpers/h record-id)
          { :update (success-fn (str "row-" record-id) :replace)
            :action "ajax-show"
            :controller model-name,
            :params { :id record-id }
            :html-options
              { :href (view-utils/url-for
                        { :action "show", :controller model-name, :params { :id (:id record) } }) } })]
      (let [record-key-str (conjure-str-utils/str-keyword record-key)]
        (if (. record-key-str endsWith "_id")
          (let [belongs-to-model (conjure-str-utils/strip-ending record-key-str "_id")
                belongs-to-id (helpers/h (get record record-key))]
            [:td (link-to belongs-to-id { :controller belongs-to-model, :action "show", :id belongs-to-id })])
          [:td (helpers/h (get record record-key))])))))