## Technical assessment

The assessment solution is based on the original `Gilded Rose Refactoring Kata` and was cloned from https://github.com/emilybache/GildedRose-Refactoring-Kata. As the submission is Java-only, non-Java content was removed from the `develop` branch. The original code base and README.md are on the `main` branch (just in case it is required as reference for review).

## How to use the repository

#### 1. Structure

The assessment documentation is organized in three parts. Please read documents for better understanding of the implementation.
- [Problem statement](docs/problemStatement.md). Lists problems and flaws with the initial solution on different abstract levels.
- [Solution designs](docs/solutionDesigns.md). Describes possible solutions, explain their pros and cons for solving problems defined in the [Problem statement](docs/problemStatement.md). Explains chosen approach. 
- [Execution plan](docs/executionPlan.md). Execution plan of the refactoring.

Implementation and unit tests live under `Java/src/main/java` and `Java/src/test/java` respectively.

#### 2. Branching model

Branching follows Git Flow:
- `main` — original upstream kata (unchanged).
- `develop` — assessment work; this is the branch to review. It is the default branch on GitHub.
- `feat/*` — short-lived branches for small changes, branched from and merged back into `develop` only.

Commits follow the [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) specification.

#### 3. Testing

How to run tests is described in the [Java README](Java/README.md).

#### 4. Misc

- The implementation follows [GildedRoseRequirements.md](GildedRoseRequirements.md) only. Nothing beyond the spec was added (for example: validating `Item#quality` up front, changing access modifiers on `Item`, or adding getters/setters).
- No new dependencies were added. The requirements do not mentioned anything beyond the JDK. For instance, framework such as Spring Boot could reduce boilerplate but is out of scope for this task.
- Refactoring was done in short-lived feature branches merged into `develop`. Those branches remain in the repository. As noted above, the branch to review is `develop`.