package com.z0ltan.compilers.microenglish.parser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.junit.Assert.assertEquals;

import com.z0ltan.compilers.microenglish.scanner.Token;
import com.z0ltan.compilers.microenglish.scanner.TokenType;

public class ParserTest extends TestCase {
  public ParserTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(ParserTest.class);
  }

  public void test1() {
    String source = "I see the cat.";
    Parser parser = new Parser(source);
    parser.parse();
  }

  public void test2() {
    String source = "I like me.";
    Parser parser = new Parser(source);
    parser.parse();
  }

  public void test3() {
    String source = "I sees the mat.";
    Parser parser = new Parser(source);
    parser.parse();
  }
}
