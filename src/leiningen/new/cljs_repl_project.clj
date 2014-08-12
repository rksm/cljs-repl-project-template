(ns leiningen.new.cljs-repl-project
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "cljs-repl-project"))

(defn cljs-repl-project
  "Creates the project files"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' cljs-repl-project project.")

    (->files
     data
     [".gitignore"                             (render ".gitignore" data)]
     ["LICENSE"                                (render "LICENSE" data)]
     ["README.md"                              (render "README.md" data)]
     ["doc/intro.md"                           (render "doc/intro.md" data)]
     ["project.clj"                            (render "project.clj" data)]
     ["src/clj/{{sanitized}}/core.clj"         (render "clj/core.clj" data)]
     ["src/clj/{{sanitized}}/routes.clj"       (render "clj/routes.clj" data)]
     ["test/clj/{{sanitized}}/core_test.clj"   (render "test/clj/core_test.clj" data)]
     ["src/cljs/{{sanitized}}/core.cljs"       (render "cljs/core.cljs" data)]
     ["test/cljs/{{sanitized}}/core_test.cljs" (render "test/cljs/core_test.cljs" data)]
     ["src/cljx/{{sanitized}}/everywhere.cljx" (render "cljx/everywhere.cljx" data)]
     ["resources/public/example.html"          (render "resources/public/example.html" data)]
     ["scripts/build.sh"                       (render "scripts/build.sh" data) :executable true]
     ["scripts/start-web-server.sh"            (render "scripts/start-web-server.sh" data) :executable true]
     ["scripts/start-repl-server.sh"           (render "scripts/start-repl-server.sh" data) :executable true])))


;; -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

(comment
  (require '[leiningen.new.templates :as temp])
  (require '[leiningen.new.cljs-repl-project :as proj])
  (require '[leiningen.core.main :as lein])
  (binding [lein/*exit-process?* false]
    (let [name "cljs-repl-project"
          data {:name name
                :sanitized (temp/name-to-path name)}]
      (sh "rm" "-rf" "cljs-repl-project")
      (->files data ["scripts/start-repl-server.sh"
                     (render "scripts/start-repl-server.sh" data)
                     :executable true])
      ))

  (binding [lein/*exit-process?* false] (cljs-repl-project "cljs-repl-project"))

  (rksm.repl.utils/lein install)
  (rksm.repl.utils/lein localrepo install
                        "target/lein-template-0.1.0-SNAPSHOT.jar"
                        "cljs-repl-project/lein-template" "0.1.0-SNAPSHOT")
  )
