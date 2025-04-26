# postings

A Clojure library designed to handle a simple posts implementation with a REST API and basic handlers for it.

## Install
First, make sure you have [lein](https://leiningen.org/) installed and run the following command:

```bash
lein deps
```

## Migration
With your database up, run the following command:

```bash
lein migratus migrate
```

For rollbacks:
```bash
lein migratus down
```

Creating a new migration:
```bash
lein migratus create new-migration-name
```

## Running
After installing the dependencies, run the following command:

```bash
lein run
```

You can access your api locally at http://localhost:3000

## Lint and Formatting
Ensure you have installed [cljfmt](https://github.com/weavejester/cljfmt)

For lint check:
```bash
cljfmt check
```

For formatting:
```bash
cljmt fix
```
