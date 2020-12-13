package com.z0ltan.compilers.microenglish.scanner;

import com.z0ltan.compilers.microenglish.error.ErrorReporter;

public class Scanner {
  private Source source;
  private Source.Character currentChar;
  private TokenType currentKind;
  private StringBuffer currentSpelling;
  private int currentLine;
  private int currentColumn;

  public Scanner(String input) {
    this.source = new Source(input);
    this.currentKind = null;
    this.currentSpelling = null;
    this.currentLine = -1;
    this.currentColumn = -1;
    this.currentChar = source.nextCharacter();
  }

  void take(char expectedChar) {
    if (currentChar.character != expectedChar) {
      ErrorReporter.report("expected to take character " + expectedChar + ", got character " + currentChar.character, 
          currentLine,
          currentColumn);
    }

    currentSpelling.append(currentChar.character);
    currentChar = source.nextCharacter();
  }

  void takeIt() {
    currentSpelling.append(currentChar.character);
    currentChar = source.nextCharacter();
  }

  void skipIt() {
    currentChar = source.nextCharacter();
  }

  void scanSeparator() {
    switch (currentChar.character) {
      case '!':
        skipIt();
        while (currentChar.character != '\n') {
          skipIt();
        }
        skipIt();
        break;

      case ' ':
      case '\n':
        skipIt();
        break;
    }
  }

  TokenType scanToken() {
    currentLine = currentChar.line;
    currentColumn = currentChar.column;

    switch (currentChar.character) {
      case 'a':
      case 'b':
      case 'c':
      case 'd':
      case 'e':
      case 'f':
      case 'g':
      case 'h':
      case 'i':
      case 'j':
      case 'k':
      case 'l':
      case 'm':
      case 'n':
      case 'o':
      case 'p':
      case 'q':
      case 'r':
      case 's':
      case 't':
      case 'u':
      case 'v':
      case 'w':
      case 'x':
      case 'y':
      case 'z':
      case 'A':
      case 'B':
      case 'C':
      case 'D':
      case 'E':
      case 'F':
      case 'G':
      case 'H':
      case 'I':
      case 'J':
      case 'K':
      case 'L':
      case 'M':
      case 'N':
      case 'O':
      case 'P':
      case 'Q':
      case 'R':
      case 'S':
      case 'T':
      case 'U':
      case 'V':
      case 'W':
      case 'X':
      case 'Y':
      case 'Z':
        takeIt();
        while (Character.isLetter(currentChar.character)) {
          takeIt();
        }
        return TokenType.IDENTIFIER;

      case '.':
        takeIt();
        return TokenType.PERIOD;

      case '\u0000':
        takeIt();
        return TokenType.EOF;

      default:
        ErrorReporter.report("unexpected character " + currentChar.character, currentLine, currentColumn);
    }

    return TokenType.ILLEGAL;
  }

  public Token scan() {
    while (Character.isWhitespace(currentChar.character) || currentChar.character == '!') {
      scanSeparator();
    }

    currentSpelling = new StringBuffer("");
    currentKind = scanToken();
    if (currentKind == TokenType.ILLEGAL) {
      ErrorReporter.report("something went wrong - illegal token kind", -1, -1);
    }

    return new Token(currentKind, currentSpelling.toString(), currentLine, currentColumn);
  }
}
