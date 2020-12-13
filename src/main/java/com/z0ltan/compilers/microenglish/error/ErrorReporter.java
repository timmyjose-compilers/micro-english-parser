package com.z0ltan.compilers.microenglish.error;

public abstract class ErrorReporter {
  public static void report(String message, int line, int column) {
    String formattedMessage = "At line " + line + ", column " + column + ", error = " + message;
    throw new MicroEnglishException(formattedMessage);
  }
}
