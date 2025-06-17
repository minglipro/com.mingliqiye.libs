package com.mingliqiye.libs.jna;

import com.mingliqiye.libs.util.SystemUtil;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import java.time.Instant;

public class ExtendedKernel32Lib {

  private static final ExtendedKernel32 kernel32 = loadKernel32();

  private static ExtendedKernel32 loadKernel32() {
    return Native.load("kernel32", ExtendedKernel32.class);
  }

  public interface ExtendedKernel32 extends Kernel32 {
    void GetSystemTimePreciseAsFileTime(FILETIME lpFileTime);
  }

  public static Instant getHighPrecisionInstant() {
    if (
      !SystemUtil.isWindows() || SystemUtil.isjdk8plus()
    ) return Instant.now();
    FILETIME fileTime = new FILETIME();
    kernel32.GetSystemTimePreciseAsFileTime(fileTime);
    long time =
      (new Integer(fileTime.dwHighDateTime).longValue() << 32) |
      (new Integer(fileTime.dwLowDateTime).longValue() & 0xFFFFFFFFL);
    final long EPOCH_OFFSET = 116444736000000000L;
    long epoch100ns = time - EPOCH_OFFSET;

    long seconds = epoch100ns / 10_000_000;
    long nanos = (epoch100ns % 10_000_000) * 100;

    return Instant.ofEpochSecond(seconds, nanos);
  }
}
