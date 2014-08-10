(ns {{name}}.core
  (:require [clojure.browser.repl :as default-repl]
            [weasel.repl :as weasel-repl]))

(def use-weasel true)

(if use-weasel
  (if-not (weasel-repl/alive?)
    (weasel-repl/connect "ws://localhost:9001" :verbose true))
  (default-repl/connect "http://localhost:9000/repl"))
