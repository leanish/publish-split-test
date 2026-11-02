# publish-split-test

A disposable fixture: it tests a publishing workflow that never runs Gradle while holding a write token. `prepare`
builds and stages the Maven files with a read-only token, `publish` uploads exactly those files to GitHub Packages
with no checkout and no build tool, and `verify` consumes them from scratch. See
[.github/workflows/publish.yml](.github/workflows/publish.yml).

The packages published here (`io.github.leanish.test:*`) are test data with no use outside this experiment. This
repository and its packages are meant to be deleted when the experiment ends.
