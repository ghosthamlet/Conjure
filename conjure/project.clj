(defproject conjure "0.8.5-SNAPSHOT"
  :description "Self extracting jar file for Conjure. Conjure is a full stack web framework written entirely in Clojure."

  :dependencies [[conjure-script "0.8.5-SNAPSHOT"]]

  :dev-dependencies [[lein-clojars "0.5.0"]]

  :hooks [leiningen.hooks.copy-resource-deps]

  :main conjure.extract)