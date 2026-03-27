## Solution designs

This document describes options to improve the implementation based on problems listed in the [problemStatement.md](problemStatement.md). Each option is judged against following problems:
- architectural flaws (tight coupling, SRP/OCP, lack of abstractions)
- unit test gaps
- `GildedRose#updateQuality` design problems
- `Item` class design problems


### Option A — Refactor the GildedRose#updateQuality method only

1. Keep `GildedRose` and `Item` as they are (`Item` should stay as-is per requirements, so there is no option to change modifiers of public fields).
2. Refactor `GildedRose#updateQuality` as follows:
   - split into private methods without changing behavior.
   - simplify nesting.
   - introduce constants for names and thresholds.
   - use descriptive variable names.
   - use `for-each` instead of `for` loop.

This approach fixes problems partially and has its limitations, which are described in tables below.

| Problem | How solution fixes problem                                                                                                       |
|---------|----------------------------------------------------------------------------------------------------------------------------------|
| Complex branching and deep nesting | Smaller methods with descriptive names reduce complexity, clarify the order of steps, and make code more readable and maintainable |
| Duplicated conditions | Repeated `if`-statements are extracted to separate reusable methods                                                                |
| Magic strings and numbers | Constants are self-descriptive and centralized                                                                                   |
| Tight coupling | Small methods with single responsibility make implementation more readable and maintainable                                      |
| Unit test gaps | Separate extracted methods can make it easier to add focused tests                                                               |
| Non-descriptive variables | Self-explanatory variables make implementation more readable and maintainable |
| Using `for-each` loop instead of `for` | Makes implementation less verbose                                                                                                |


| Problem                    | Limitation of solution                                                                               |
|----------------------------|------------------------------------------------------------------------------------------------------|
| Open/Closed Principle      | Adding new Item kinds still requires changing the method                                            |
| Unit test per rule | Some tests still exercise `GildedRose#updateQuality` end-to-end                                      |
| Lack of abstractions       | No domain types for Item kinds. No update behavior abstractions                                      |
| Single Responsibility Principle | `GildedRose#updateQuality` is still a 'God-method' that is responsible for orchestration and behavior |


### Option B - Introduce Item subclasses

1. Introduce types such as `AgedBrie`, `NormalItem`, `SulfurasItem`, etc. extending `Item`.
2. `Item` stays as-is per requirements restriction. A new method, like `update` to be overridden in sub-classes, can't be added.
3. Introduce factory to create instances of the new types.
4. Refactor `GildedRose#updateQuality` as follows:
   - refactoring can be similar to the one described in the Option A.
   - polymorphism is used to work with `Item` types instead of String-based names.

This approach fixes problems partially but also has its limitations, which are described in tables below.

| Problem                     | How solution fixes problem                                                                                                       |
|-----------------------------|----------------------------------------------------------------------------------------------------------------------------------|
| Lack of abstractions        | Domain model matches domain language. Implementation operates with polymorphism. Safe and clear extension of the model with new types. Business rules tied up with types |
| Coupling in a single method | Behaviour can move toward subclasses instead of an `if`-statements tree |


| Problem                    | Limitation of solution                                                                                                                                            |
|----------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Open/Closed Principle | Not solved. Each new type may require changes in the `GildedRose#updateQuality`                                                                                   |
| `GildedRose#updateQuality` is still a 'God-method' | Even refactoring of the `GildedRose#updateQuality` keeps all the business rules in the single class and doesn't allow rules to be tied to types at the domain level |  
| Unit tests per rule | It remains pretty extensive, as tests must traverse the whole `GildedRose#updateQuality` |


### Option C - Implement Strategy pattern

1. Introduce `ItemUpdateStrategy` interface that declares `update` behavior.
2. Add `ItemUpdateStrategy` implementations per `Item` kind.
3. Add factory or registry for `ItemUpdateStrategy` implementations that derives strategy from the name.
4. `GildedRose#updateQuality` only iterates over `Item` instances and delegates update to the `ItemUpdateStrategy` implementations.

Pros and cons of this solution are in tables below.

| Problem                           | How solution fixes problem                                                                                     |
|-----------------------------------|----------------------------------------------------------------------------------------------------------------|
| Lack of abstractions              | Domain model is based on behavior abstractions applicable per `Item` kind                                      |
| Coupling in a single method       | Each strategy owns rules for a single `Item` kind. `GildedRose` only orchestrates the loop                     |
| Open/Closed Principle             | Each new type only introduces new strategy. No changes in existing classes, means code closed for modification |
| Single Responsibility Principle   | Each class has its own responsibility                                                                          |
| Tight coupling                    | Each class doesn't know anything about other classes and doesn't depend on other classes' behavior             |
| Complex branching and deep nesting | Not present anymore, as business rules are described in each particular strategy                               |
| Unit test per rule                | Strategies can be unit-tested in isolation, without the full `GildedRose` graph                                |
| Poor readability | Introducing strategies, keeping SRP makes code simple, intelligible, with good readability                     |


| Problem | Limitation of solution |
|---------|------------------------|
| `Item` encapsulation | Remains unchanged, thus poor |
| Strategy resolution is still based on `Item` name | Still string-driven |

### Recommended approach

**Option C**, optionally preceded by Option A (extract methods and constants under tests) so the refactoring keeps the application safe.
