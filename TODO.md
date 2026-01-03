# TODOs

- The recipe registration in RecipeProvider should be eventually moved to its own registry package, which contains an interface and relevant logic for making recipe registration even more fluent
- There are a lot of legacy functions which generate recipes in CombatWeaponryPlus.java. These should be ported to use the WeaponBuilder/ItemBuilder APIs along with any other wrapper.
- There seems to be inconsistent usage of `Objects.requireNonNull` and `Preconditions.checkNotNull`. These should be straightened or defined somewhere in a code style doc.
