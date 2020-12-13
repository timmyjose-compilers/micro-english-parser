package com.z0ltan.compilers.microenglish.scanner;

import java.util.Map;
import java.util.HashMap;

public class Token {
  public TokenType kind;
  public String spelling;
  public int line;
  public int column;

  private static final Map<String, TokenType> keywords;

  static {
    keywords = new HashMap<>();

    keywords.put("a", TokenType.A);
    keywords.put("cat", TokenType.CAT);
    keywords.put("i", TokenType.I);
    keywords.put("mat", TokenType.MAT);
    keywords.put("me", TokenType.ME);
    keywords.put("rat", TokenType.RAT);
    keywords.put("see", TokenType.SEE);
    keywords.put("sees", TokenType.SEES);
    keywords.put("is", TokenType.IS);
    keywords.put("like", TokenType.LIKE);
    keywords.put("the", TokenType.THE);
  }

  public Token(TokenType kind, String spelling, int line, int column) {
    this.spelling = spelling;
    if (Token.isKeyword(spelling.toLowerCase())) {
      this.kind = Token.getKeywordKind(spelling.toLowerCase());
    } else {
      this.kind = kind;
    }
    this.line = line;
    this.column = column;
  }

  public Token(TokenType kind, String spelling) {
    this(kind, spelling, -1, -1);
  }

  public static boolean isKeyword(String spelling) {
    return Token.keywords.containsKey(spelling);
  }

  public static TokenType getKeywordKind(String spelling) {
    return Token.keywords.get(spelling);
  }

  @Override
  public String toString() {
    return "<kind=" + this.kind + ", spelling=" + this.spelling + ">";
  }
}
