(ns new-day
  (:require
   [babashka.curl :as curl]
   [babashka.fs :as fs]
   [clojure.string :as str]))

(defn strip-leading-zero [day]
  (str/replace day #"^0" ""))

(defn ensure-leading-zero [day]
  (if (= 1 (count day))
    (str "0" day)
    day))

(defn new-day
  {:org.babashka/cli {:coerce {:day :string
                               :year :string}}}
  [{:keys [year day]
    :or {year "22"}}]
  (let [new-year (str "aoc" year)
        day-number day
        new-day (str "day"
                     (ensure-leading-zero day-number))
        day01 (slurp (fs/file "src" "aoc22" "day01.cljc"))
        replaced (str/replace day01 "aoc22" new-year)
        replaced (str/replace replaced "day01" new-day)]
    (fs/create-dirs "src" new-year)
    (fs/create-dirs "resources" new-year)
    (let [new-day-resource (fs/file "resources" new-year (str new-day ".txt"))]
      (if-not (fs/exists? new-day-resource)
        (if-let [session (System/getenv "AOC_SESSION")]
          (let [input-url (format "https://adventofcode.com/2022/day/%s/input" (strip-leading-zero day-number))]
            (println "Retrieving input from" input-url)
            (let [body (:body (curl/get input-url {:headers {"Cookie" (str "session=" session)
                                                             "User-Agent" "github.com/borkdude/advent-of-babashka-template by michielborkent@gmail.com"}}))]
              (spit new-day-resource body)))
          (do (println "Tip: set the AOC_SESSION environment variable to the Advent of Code session cookie to retrieve your input automatically!")
              (println "For now, a copy of the input of day 1 is copied as a stub.")
              (fs/copy (fs/file "resources" "aoc22" "day01.txt")
                       new-day-resource)))
        (println (format "File %s already exists." (str new-day-resource)))))
    (let [new-day (fs/file "src" new-year (str new-day ".cljc"))]
      (if-not (fs/exists? new-day)
        (spit new-day replaced)
        (println (format "File %s/%s already exists." new-year new-day))))))
