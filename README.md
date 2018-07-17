# Fizz-Buzz with monads

Simple repository to implement the famous Fizz-Buzz Kata
in Java with the help of [VAVR](https://vavr.io) with
the magic powers of monads.

## Rules of Fizz-Buzz

in case you didn't know:

|Case|Result|
|---|---|
|numbers divisible by 3|Fizz|
|numbers divisible by 5|Buzz|
|numbers divisible by 3 and 5|Fizz-Buzz|
|everything else|number itself|

## Rules for the Kata

Try to avoid all control structures of Java at all costs.
This includes:

|Control structure|VAVR substitute|
|---|---|
|if, ternary statement|Option|
|for, foreach|filter/map/reduce on a Collection type|
|while|Stream.unfold|
|try|Try or Either|
|switch|Match|

## Suggested test cases

- one parameterized test for each case from above
- test for a sequence of Fizz-Buzz numbers
- input validation (such as numbers <= 0 or really big numbers)