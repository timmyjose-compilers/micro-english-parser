package com.z0ltan.compilers.microenglish;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.z0ltan.compilers.microenglish.parser.Parser;
import com.z0ltan.compilers.microenglish.error.MicroEnglishException;

public class App {
  private static final String PROMPT = ">> ";

  public static void main(String[] args) {
    System.out.println("Welcome to the Micro-English repl. Press Ctrl+D (or Ctrl+C) to quit...\n");

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      while (true) {
        System.out.print(PROMPT);
        System.out.flush();

        String line = reader.readLine();
        if (line == null) {
          break;
        }

        Parser parser = new Parser(line);
        try {
          parser.parse();
        } catch (MicroEnglishException ex) {
          System.out.println("Parsing failed. Reason: " + ex.getLocalizedMessage() + "\n");
          continue;
        }
        System.out.println("valid sentence!\n");
      }
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
