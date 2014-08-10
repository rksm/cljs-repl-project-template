(ns {{name}}.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(comment
  (require 'cljs.repl.browser)

  (cemerick.piggieback/cljs-repl
   :repl-env (cljs.repl.browser/repl-env :port 9000))

  123
  :cljs/quit

  ;; -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-


  (require '[cljs.repl :as repl])
  (require '[cljs.repl.browser :as browser])
  (def env (browser/repl-env))
  (repl/repl env)


  ;; -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

  (require 'weasel.repl.websocket)
  (cemerick.piggieback/cljs-repl
   :repl-env (weasel.repl.websocket/repl-env
              :ip "0.0.0.0" :port 9001))

  ;; (js/alert 123)
  ;; :cljs/quit
  (lein help repl)
  )
