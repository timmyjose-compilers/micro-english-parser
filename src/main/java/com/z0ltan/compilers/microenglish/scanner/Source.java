package com.z0ltan.compilers.microenglish.scanner;

import java.util.List;
import java.util.ArrayList;

public class Source {
  static class Character {
    public char character;
    public int line;
    public int column;

    public static final Character NUL = new Character('\u0000', -1, -1);

    public Character(char character, int line, int column) {
      this.character = character;
      this.line = line;
      this.column= column;
    }
  }
  private List<Character> characters;
  private int idx;

  public Source(String source) {
    this.characters = new ArrayList<>();

    int line = 1, column = 1;
    for (char c : source.toCharArray()) {
      if (c == '\n') {
        line++;
        column = 1;
      }
      characters.add(new Character(c, line, column));
      column++;
    }
    this.idx = 0;
  }

  public Character nextCharacter() {
    if (idx >= characters.size()) {
      return Character.NUL;
    }
    return characters.get(idx++);
  }
}

