package com.mingliqiye.libs.util;

public class StringUtil {

  public static String toString(Object obj) {
    return obj == null ? null : obj.toString();
  }

  public static String format(String format, Object... args) {
    return String.format(format.replace("{}", "%s"), args);
  }
}
