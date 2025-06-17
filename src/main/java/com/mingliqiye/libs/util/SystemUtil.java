package com.mingliqiye.libs.util;

import lombok.Getter;

public class SystemUtil {

  @Getter
  private static final String osName = System.getProperties()
    .getProperty("os.name");

  public static boolean isWindows() {
    return osName != null && osName.startsWith("Windows");
  }

  public static boolean isMac() {
    return osName != null && osName.startsWith("Mac");
  }

  public static boolean isUnix() {
    return (
      (osName != null && osName.startsWith("Linux")) ||
      (!isWindows() && !isMac())
    );
  }

  public static String getJdkVersion() {
    return System.getProperty("java.specification.version");
  }

  public static Integer getJavaVersionFloat() {
    String version = getJdkVersion();
    String uversion;
    if (version.startsWith("1.")) {
      uversion = version.substring(2, 3);
    } else {
      uversion = version.substring(0, 2);
    }
    return Integer.parseInt(uversion);
  }

  public static boolean isjdk8plus() {
    return getJavaVersionFloat() > 8;
  }
}
