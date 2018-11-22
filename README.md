# Cafe X

## Caveats

This is clearly nowhere near production code,
but is
>_"the simplest possible solution which meets our requirement"_

given the fixed, limited menu, and fixed service charges.

Clearly, for any serious application, you would want to
* implement proper classes for order items, with hot/cold and food/drink as traits or properties
* generalise the implementation for any hot/cold foods
* parameterise the service charge rates and ceiling amount
* hydrate the menu object from eg. yaml config, instead of explicitly declaring it


Lobster, a premium item, is added to the menu at a cost of £25
When purchased items include any premium food apply a service charge of 25% to the total bill to a max of £40