package com.z0ltan.compilers.microenglish.scanner;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.junit.Assert.assertEquals;

public class ScannerTest extends TestCase {
  static class ScannerTestCase {
    TokenType expectedKind;
    String expectedSpelling;

    public ScannerTestCase(TokenType expectedKind, String expectedSpelling) {
      this.expectedKind = expectedKind;
      this.expectedSpelling = expectedSpelling;
    }
  }

  public ScannerTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(ScannerTest.class);
  }

  public void test1() {
    ScannerTestCase[] testCases = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.I, "I"),
          new ScannerTestCase(TokenType.SEE, "see"),
          new ScannerTestCase(TokenType.THE, "the"),
          new ScannerTestCase(TokenType.CAT, "cat"),
          new ScannerTestCase(TokenType.PERIOD, "."),
    };

    String source = "I see the cat.";
    Scanner scanner = new Scanner(source);
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].expectedKind, token.kind);
      assertEquals(testCases[i].expectedSpelling, token.spelling);
    }

    assertEquals(scanner.scan().kind, TokenType.EOF);
  }

  public void test2() {
    ScannerTestCase[] testCases = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.I, "I"),
          new ScannerTestCase(TokenType.LIKE, "like"),
          new ScannerTestCase(TokenType.ME, "me"),
          new ScannerTestCase(TokenType.PERIOD, "."),
    };

    String source = "I like me.";
    Scanner scanner = new Scanner(source);
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].expectedKind, token.kind);
      assertEquals(testCases[i].expectedSpelling, token.spelling);
    }

    assertEquals(scanner.scan().kind, TokenType.EOF);
  }

  public void test3() {
    ScannerTestCase[] testCases = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.I, "I"),
          new ScannerTestCase(TokenType.SEES, "sees"),
          new ScannerTestCase(TokenType.THE, "the"),
          new ScannerTestCase(TokenType.MAT, "mat"),
          new ScannerTestCase(TokenType.PERIOD, "."),
    };

    String source = "I sees the mat.";
    Scanner scanner = new Scanner(source);
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].expectedKind, token.kind);
      assertEquals(testCases[i].expectedSpelling, token.spelling);
    }

    assertEquals(scanner.scan().kind, TokenType.EOF);
  }
}

