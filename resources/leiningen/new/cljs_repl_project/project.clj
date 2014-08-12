(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-ring "0.8.7"]
            [com.keminglabs/cljx "0.4.0"]
            [lein-cljsbuild "1.0.4-SNAPSHOT"]
            [com.cemerick/clojurescript.test "0.3.1"]]
  :ring {:handler {{name}}.routes/app}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 ;;; FIXME currently a brepl seems only to work with 2202 or
                 ;;; older...
                 [org.clojure/clojurescript "0.0-2202"]
                 [org.clojure/core.async "0.1.303.0-886421-alpha"]

                 [com.cemerick/piggieback "0.1.3"]
                 [weasel "0.3.0"]
                 [com.cemerick/clojurescript.test "0.3.1"]

                 [compojure "1.1.5"]]

  :source-paths ["src/clj"]

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}
  ;; FIXME this should autoload a weasel repl but currently throws
  ;; an error won startup
  ;; :cljs-weasel-repl
  ;; {:repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]
  ;;                 :init (do (require 'weasel.repl.websocket)
  ;;                           (require 'cemerick.piggieback)
  ;;                           (cemerick.piggieback/cljs-repl
  ;;                            :repl-env (weasel.repl.websocket/repl-env
  ;;                                       :ip "0.0.0.0" :port 9001)))}}

  :cljx {:builds [{:source-paths ["src/cljx"]
                   :output-path "target/classes"
                   :rules :clj}
                  {:source-paths ["src/cljx"]
                   :output-path "target/generated/cljs"
                   :rules :cljs}]}
  :hooks [cljx.hooks]

  :cljsbuild {:builds {:example {:source-paths ["src/cljs" "target/generated/cljs"]
                                 :compiler {:output-to "resources/public/js/example.js"
                                            :optimizations :whitespace}}
                       :tests {:source-paths ["src/cljs" "target/generated/cljs" "test/cljs"]
                               :compiler {:output-to "target/js/tests.js"
                                          :optimizations :whitespace}}}
              :test-commands {"unit-tests" ["phantomjs" :runner
                                            "this.literal_js_was_evaluated=true"
                                            "target/js/tests.js"]}})
