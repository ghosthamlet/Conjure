(ns conjure.view.util
  (:require [clojure.contrib.seq-utils :as seq-utils]
            [conjure.util.file-utils :as file-utils]
            [conjure.util.loading-utils :as loading-utils]))

(defn 
#^{:doc "Finds the views directory which contains all of the files which describe the html pages of the app."}
  find-views-directory []
  (seq-utils/find-first (fn [directory] (. (. directory getPath) endsWith "views"))
    (. (loading-utils/get-classpath-dir-ending-with "app") listFiles)))
  
(defn
#^{:doc "Finds a controller directory for the given controller in the given view directory."}
  find-controller-directory [view-directory controller]
  (file-utils/find-directory view-directory (loading-utils/dashes-to-underscores controller)))
  
(defn
#^{:doc "Finds a view file with the given controller-directory and action."}
  find-view-file [controller-directory action]
  (file-utils/find-file controller-directory (loading-utils/symbol-string-to-clj-file action)))
  
(defn
#^{:doc "Loads the view corresponding to the values in the given request map."}
  load-view [request-map]
  (loading-utils/load-resource 
    (str "views/" (loading-utils/dashes-to-underscores (:controller request-map))) 
    (str (loading-utils/dashes-to-underscores (:action request-map)) ".clj")))

(defn
#^{:doc "Returns the view namespace request map."}
  request-view-namespace [request-map]
  (str "views." (:controller request-map) "." (:action request-map)))
  
(defn
#^{:doc "Returns the view namespace for the given view file."}
  view-namespace [controller view-file]
  (request-view-namespace 
    { :controller controller 
      :action (loading-utils/clj-file-to-symbol-string (. view-file getName)) }))