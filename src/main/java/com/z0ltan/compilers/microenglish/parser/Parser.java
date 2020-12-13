package com.z0ltan.compilers.microenglish.parser;

import com.z0ltan.compilers.microenglish.scanner.Token;
import com.z0ltan.compilers.microenglish.scanner.TokenType;
import com.z0ltan.compilers.microenglish.scanner.Scanner;
import com.z0ltan.compilers.microenglish.error.ErrorReporter;

public class Parser {
  private Scanner scanner;
  private Token currentToken;

  public Parser(String input) {
    this.scanner = new Scanner(input);
  }

  void acceptIt() {
    currentToken = scanner.scan();
  }

  void accept(TokenType expectedKind) {
    if (currentToken.kind != expectedKind) {
      ErrorReporter.report("expected to accept token of kind " + expectedKind + ", got token of kind " + currentToken.kind,
          currentToken.line,
          currentToken.column);
    }

    currentToken = scanner.scan();
  }

  // program ::= sentence
  public void parse() {
    currentToken = scanner.scan();
    parseSentence();
  }

  // sentence ::= Subject Verb Object
  void parseSentence() {
    parseSubject();
    parseVerb();
    parseObject();
    accept(TokenType.PERIOD);
  }

  // subject ::= I | a Noun | the Noun
  void parseSubject() {
    switch (currentToken.kind) {
      case I:
        acceptIt();
        break;

      case A:
      case THE:
        acceptIt();
        parseNoun();
        break;

      default:
        ErrorReporter.report("expected \"I\", \"a\", or \"the\", got \"" + currentToken.spelling + "\"", 
            currentToken.line,
            currentToken.column);
    }
  }

  // noun ::= cat | mat | rat
  void parseNoun() {
    switch (currentToken.kind) {
      case CAT:
      case MAT:
      case RAT:
        acceptIt();
        break;

      default:
        ErrorReporter.report("expected \"cat\", \"mat\", or \"rat\", got \"" + currentToken.spelling + "\"",
            currentToken.line,
            currentToken.column);
    }
  }

  // verb ::= like | is | see | sees
  void parseVerb() {
    switch (currentToken.kind) {
      case LIKE:
      case IS:
      case SEE:
      case SEES:
        acceptIt();
        break;

      default:
        ErrorReporter.report("expected \"like\", \"is\", \"see\", or \"sees\", got \"" + currentToken.spelling + "\"",
            currentToken.line,
            currentToken.column);
    }
  }

  // object ::= me | a Noun | the Noun
  void parseObject() {
    switch (currentToken.kind) {
      case ME:
        acceptIt();
        break;

      case A:
      case THE:
        acceptIt();
        parseNoun();
        break;

      default:
        ErrorReporter.report("expected \"me\", \"a\", or \"the\", got \"" + currentToken.spelling + "\"",
            currentToken.line,
            currentToken.column);
    }
  }
}
