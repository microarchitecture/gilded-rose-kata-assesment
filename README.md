## Technical assessment

The assessment solution is based on the original `Gilded Rose Refactoring Kata` and was cloned from https://github.com/emilybache/GildedRose-Refactoring-Kata. As the submission is Java-only, non-Java content was removed on the development branch. The original code base and README.md are on the `main` branch.

## How to use the repository

#### 1. Structure

The assessment is organized in three main steps:
- [Problem statement](docs/problemStatement.md)
- [Solution designs](docs/solutionDesigns.md)
- [Execution plan of the refactoring](docs/executionPlan.md)

Implementation and unit tests live under `Java/src/main/java` and `Java/src/test/java` respectively.

#### 2. Branching model

Branching follows Git Flow:
- `main` — original upstream kata (unchanged).
- `develop` — assessment work; this is the branch to review. It is the default branch on GitHub.
- `feat/*` — short-lived branches for small changes, branched from and merged back into `develop` only.

Commits follow the [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) specification.

#### 3. Testing

How to run tests is described in the [Java README](Java/README.md).