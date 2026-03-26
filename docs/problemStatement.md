## Problem statement

This explains why the original Java code should be refactored. Starting points are:
    - `GildedRose#updateQuality` method.
    - `GildedRoseTest` test class.
    - `Item` class.
    - requirements from the [GildedRoseRequirements.md](GildedRoseRequirements.md)

Problems found are grouped into the following categories:  

#### Architectural problems
1. Tight coupling:
   - mostly all the business rules backed by requirements expressed in the single method. Different behaviors applicable to each Item type are not isolated, but mixed in the single execution flow.    
   - every case 'knows' about the rest of the method. There is no option to test only single rule as isolated unit (for instance, backstage rule) without running the whole update.
2. Object-Oriented Design flaws:
   - violate Single Responsibility principle. `GildedRose` class responsible for mostly everything: contains business rules, processes all inventories, and change inventory states.
   - violate Open/Closed principle. New rules require changing existing code in the `GildedRose#updateQuality`. It increases risk every time when rules are added or changed.
   - no clear domain abstractions. Item has different kinds that could be abstracted.  

#### Unit test gaps
1. There are no tests that test current implementation behavior. Refactoring or adding new features lack a safety net.  
2. Existing `GildedRoseTest#foo` test is a placeholder. It does not describe or test any business rule. It doesn't assert any `GildedRose#updateQuality` outcome.

#### GildedRose#updateQuality problems
1. Complex branching and deep nesting. Easy to introduce defects when editing. Easy to miss a path when you add or adjust a condition. 
2. One complex method that does everything:
   - any change to a rule tends to touch the same method, which is hard to read end-to-end and review.
   - hard to test each rule in isolation. Unit tests tend to be large.
3. Duplicated conditions (like `items[i].quality < 50`) and same item name checks. Risk of updating one part of the flow and forgetting another which may lead to a defect.
4. Business logic operates by Item name instead of Item kind as type (it partially relates to the 'no clear domain abstractions' point described in the 'Object-Oriented Design flaws').
5. Error-prone ordering between `quality` updates and `sellIn`. Refactoring that reorders steps can change behavior even if each step is correct on its own.
6. Magic strings and numbers. Names and thresholds are inlined, but not extracted to self-explanatory constants. Error-prone approach especially for String values.  
7. Poor readability:
   - over-use of `i` as non-descriptive and noisy variable for the array index.
   - retrieving elements from the Item[] each time it is addressed instead of setting it to separate variable.
8. Using `for` loop instead of `for-each` makes implementation more verbose.   

#### Problems around the Item class
1. `Item` class is not encapsulated:
   - any other class that has reference to the `Item` class knows its internal implementation.
   - all fields are public thus any other class can change the state of the `Item` instances.
   - `quality` bounds can't be enforced by the `Item` type.
2. No `equals`/`hashCode` implementations. Not a defect in the current implementation, but can be an error-prone depending on usage in tests.

## Summary

The starting point is a single, complex `updateQuality()` method with the high cyclomatic complexity, duplicated checks, and ordering that should be preserved. Per requirements, `Item` class should be left as it is. Tests should be implemented and cover all the business logic. Refactoring should address design and maintainability while bringing tests and implementation in line with the requirements.  