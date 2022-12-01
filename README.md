# Advent of Babashka

A project for doing Advent of Code using
[babashka](https://github.com/babashka/babashka) and
[nbb](https://github.com/babashka/nbb).

To install babashka, check [here](https://github.com/babashka/babashka#installation). It is recommended to use version 1.0.167 or higher.
To install nbb: `npm install -g nbb`. Nbb 1.1.151 or higher is required.

The first puzzle is in `src/aoc22/day01.cljc`. You can execute part 1 in both `bb` and `nbb` using:

```
$ bb -x aoc22.day01/part-1 [...args]
$ nbb -x aoc22.day01/part-1 [...args]
```

If you want to install `nbb` locally in the project, run `npm install
nbb@latest` and use `node_modules/.bin/nbb` instead to run nbb (this starts
faster than `npx nbb`).

Each solution can take parameters from the command line if necessary.  Arguments
are parsed using
[babashka.cli](https://github.com/babashka/cli/blob/main/src/babashka/cli.cljc):

```
$ bb -x clojure.core/prn --flag --option 1
{:flag true, :option 1}
```

# License

This code is unlicensed, you can fork / clone it and use it however you want.
