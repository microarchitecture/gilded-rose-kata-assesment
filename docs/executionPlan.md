## Execution plan

1. Add unit tests that test existing logic in the `GildedRose#updateQuality`. Assertions should assert `quality` and `sellIn` after `updateQuality()` is executed. Successful tests are required before refactoring starts. Tests are indicators of defects that can be introduced during refactoring.
2. Extract logic from the `GildedRose#updateQuality` into separate methods (no behavior change). Method names should reflect the logic that is extracted. Methods are candidates to be moved into strategy classes later.
3. Add `ItemUpdateStrategy` interface that applies one day of rules for an `Item` kind.
4. Update `GildedRose#updateQuality` such that it only iterates over Items, resolves strategy, and delegates update.
5. Add `ItemUpdateStrategy` implementation per `Item` kinds: normal, AgedBrie, Sulfuras, Backstage passes, etc. Move extracted business logic (step 2) to `ItemUpdateStrategy` implementations.   
6. Add strategy resolving mechanism. Use Factory or Registry pattern for that.
7. Update tests per structure change if needed (most likely it is not).
8. Refactor and introduce other smaller improvements, if required.
9. Run tests, both unit and TestTests for the final pass.
10. Update README.md and other files if it is required.