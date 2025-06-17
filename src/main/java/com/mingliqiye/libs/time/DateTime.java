package com.mingliqiye.libs.time;

import com.mingliqiye.libs.jna.ExtendedKernel32Lib;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * com.mingliqiye.libs.Main
 * <p>自定义时间类
 * <p>jdk8使用jdk9+的windows时间API
 *
 * @author MingLiPro|Armamem0t
 * @version 1.0
 * @see java.time.LocalDateTime
 * @see java.time.ZoneId
 */
public class DateTime {

  @Getter
  public static enum Formats {
    STANDARD_DATETIME("yyyy-MM-dd HH:mm:ss"),
    STANDARD_DATETIME_MILLISECOUND7("yyyy-MM-dd HH:mm:ss.SSSSSSS"),
    STANDARD_DATETIME_MILLISECOUND6("yyyy-MM-dd HH:mm:ss.SSSSSS"),
    STANDARD_DATETIME_MILLISECOUND5("yyyy-MM-dd HH:mm:ss.SSSSS"),
    STANDARD_DATETIME_MILLISECOUND4("yyyy-MM-dd HH:mm:ss.SSSS"),
    STANDARD_DATETIME_MILLISECOUND3("yyyy-MM-dd HH:mm:ss.SSS"),
    STANDARD_DATETIME_MILLISECOUND2("yyyy-MM-dd HH:mm:ss.SS"),
    STANDARD_DATETIME_MILLISECOUND1("yyyy-MM-dd HH:mm:ss.S"),
    STANDARD_DATETIME_SECOUND("yyyy-MM-dd HH:mm:ss"),
    STANDARD_DATE("yyyy-MM-dd"),
    ISO8601("yyyy-MM-dd'T'HH:mm:ss.SSS'000'"),
    COMPACT_DATETIME("yyyyMMddHHmmss");

    private final String value;

    Formats(String value) {
      this.value = value;
    }
  }

  @Getter
  @Setter
  private LocalDateTime localDateTime;

  @Getter
  @Setter
  private ZoneId zoneId = ZoneId.systemDefault();

  @Override
  public String toString() {
    return (
      this.getClass().getName() +
      '@' +
      Integer.toHexString(hashCode()) +
      '(' +
      format(Formats.STANDARD_DATETIME_MILLISECOUND7) +
      ")"
    );
  }

  @Deprecated
  public static DateTime nowWindowsHighPrecision() {
    return new DateTime(
      LocalDateTime.ofInstant(
        ExtendedKernel32Lib.getHighPrecisionInstant(),
        ZoneId.systemDefault()
      )
    );
  }

  public static DateTime now() {
    return new DateTime(
      LocalDateTime.ofInstant(
        ExtendedKernel32Lib.getHighPrecisionInstant(),
        ZoneId.systemDefault()
      )
    );
  }

  DateTime(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }

  public static DateTime of(LocalDateTime localDateTime) {
    return new DateTime(localDateTime);
  }

  DateTime() {
    this(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
  }

  DateTime(Date date) {
    this(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
  }

  public static Date toDate(LocalDateTime localDateTime) {
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

  public static LocalDateTime toLocalDateTime(Date date) {
    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
  }

  public Date toDate() {
    return toDate(localDateTime);
  }

  public static String format(Date date, String formater) {
    return format(toLocalDateTime(date), formater);
  }

  public static String format(LocalDateTime date, Formats formats) {
    return format(date, formats.value);
  }

  public static String format(LocalDateTime date, String formater) {
    return DateTimeFormatter.ofPattern(formater).format(date);
  }

  public String format(String formater) {
    return format(localDateTime, formater);
  }

  public String format(Formats formater) {
    return format(localDateTime, formater.value);
  }

  public static DateTime plusYears(DateTime dateTime, long years) {
    dateTime.setLocalDateTime(dateTime.getLocalDateTime().plusYears(years));
    return dateTime;
  }

  public static DateTime plusMonths(DateTime dateTime, long months) {
    dateTime.setLocalDateTime(dateTime.getLocalDateTime().plusMonths(months));
    return dateTime;
  }

  public static DateTime plusWeeks(DateTime dateTime, long weeks) {
    dateTime.setLocalDateTime(dateTime.getLocalDateTime().plusWeeks(weeks));
    return dateTime;
  }

  public static DateTime plusDays(DateTime dateTime, long days) {
    dateTime.setLocalDateTime(dateTime.getLocalDateTime().plusDays(days));
    return dateTime;
  }

  public static DateTime plusHours(DateTime dateTime, long hours) {
    dateTime.setLocalDateTime(dateTime.getLocalDateTime().plusHours(hours));
    return dateTime;
  }

  public static DateTime plusMinutes(DateTime dateTime, long minutes) {
    dateTime.setLocalDateTime(dateTime.getLocalDateTime().plusMinutes(minutes));
    return dateTime;
  }

  public static DateTime plusSeconds(DateTime dateTime, long seconds) {
    dateTime.setLocalDateTime(dateTime.getLocalDateTime().plusSeconds(seconds));
    return dateTime;
  }

  public static DateTime plusNanos(DateTime dateTime, long nanos) {
    dateTime.setLocalDateTime(dateTime.getLocalDateTime().plusNanos(nanos));
    return dateTime;
  }

  public DateTime plusYears(long years) {
    return plusYears(this, years);
  }

  public DateTime plusMonths(long months) {
    return plusMonths(this, months);
  }

  public DateTime plusWeeks(long weeks) {
    return plusWeeks(this, weeks);
  }

  public DateTime plusDays(long days) {
    return plusDays(this, days);
  }

  public DateTime plusHours(long hours) {
    return plusHours(this, hours);
  }

  public DateTime plusMinutes(long minutes) {
    return plusMinutes(this, minutes);
  }

  public DateTime plusSeconds(long seconds) {
    return plusSeconds(this, seconds);
  }

  public DateTime plusNanos(long nanos) {
    return plusNanos(this, nanos);
  }
}
