(ns aoc22.day01
  (:require #?(:clj [clojure.java.io :as io]
               :cljs [nbb.core :refer [slurp await]])
            [clojure.string :as str]
            #?(:cljs [promesa.core :as p])))

#?(:clj
   (def input (->> (slurp (io/resource "aoc22/day01.txt"))
                   (str/split-lines)
                   (map parse-long)))
   :cljs
   (def input (await (p/->> (slurp "resources/aoc22/day01.txt")
                            (str/split-lines)
                            (map parse-long)))))

(defn part-1
  "Run with (n)bb -x aoc22.part-1"
  [_]
  (->> input
       (partition-by nil?)
       (take-nth 2)
       (map #(apply + %))
       (apply max)))

(defn part-2
  "Run with (n)bb -x aoc22.part-2"
  [_]
  (->> input
       (partition-by nil?)
       (take-nth 2)
       (map #(apply + %))
       (sort-by -)
       (take 3)
       (apply +)))
