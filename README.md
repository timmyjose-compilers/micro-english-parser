# micro-english-parser

A recursive-descent parser for a very small subset of English, written in Java. This language is taken from the book, "Programming Language Processors in Java" by Watt & Brown.

This parser does not construct any intermediate representation of the input. Instead, it passes quietly if the input is accepted by the parser, and fails with error messages if
the input could not be accepted by the grammar.

# Grammar

The whole grammar is as follows (in EBNF):

```
Sentence ::= Subject Verb Object PERIOD

Subject ::= I | a Noun | the Noun

Object ::= me | a Noun | the Noun

Noun ::= cat | mat | rat

Verb ::= like | is | see | sees

```

The lexical grammar is given by:

```
PERIOD ::= '.'
```

# Demo

## Build

```
$ mvn clean && mvn compile
```

## Test

```
$ mvn test
```

## Run

```
~/dev/incubator/micro-english$ mvn clean && mvn compile && mvn exec:java -Dexec.mainClass=com.z0ltan.compilers.microenglish.App

Welcome to the Micro-English repl. Press Ctrl+D (or Ctrl+C) to quit...

>> I like the cat.
valid sentence!

>> The cat sees the rat.
valid sentence!

>> I like the mat.
valid sentence!

>> A rat sees me.
valid sentence!

>> A bat sees the rat.
Parsing failed. Reason: At line 1, column 3, error = expected "cat", "mat", or "rat", got "bat"

>> A cat sees the snake.
Parsing failed. Reason: At line 1, column 16, error = expected "cat", "mat", or "rat", got "snake"

>> I likes the mat.
Parsing failed. Reason: At line 1, column 3, error = expected "like", "is", "see", or "sees", got "likes"

>> The mad cat likes the sad rat.
Parsing failed. Reason: At line 1, column 5, error = expected "cat", "mat", or "rat", got "mad"

>> I sees a mat.
valid sentence!

>> The mat sees a cat.
valid sentence!

>> to be or not to be.
Parsing failed. Reason: At line 1, column 1, error = expected "I", "a", or "the", got "to"

>> I like the rat
Parsing failed. Reason: At line -1, column -1, error = expected to accept token of kind PERIOD, got token of kind EOF

>> I . like . the . cAT.
Parsing failed. Reason: At line 1, column 3, error = expected "like", "is", "see", or "sees", got "."
```

# LICENCE

See [LICENCE](LICENSE.md)


