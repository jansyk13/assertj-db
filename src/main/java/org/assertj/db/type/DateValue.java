package org.assertj.db.type;

import java.text.ParseException;

/**
 * This class represents a date value in the database.
 * 
 * @author Régis Pouiller
 * 
 */
public class DateValue {

  /**
   * Day of the month.
   */
  private final int dayOfTheMonth;
  /**
   * Month.
   */
  private final int month;
  /**
   * Year.
   */
  private final int year;
  /**
   * Indicates where there are the digits in the {@code String} for {@link DateValue#DateValue(String)}.
   */
  private static final String DATE_FORMAT = "\\d\\d\\d\\d-\\d\\d-\\d\\d";

  /**
   * Makes an instance of date value from a day of month, a month and an year.
   * 
   * @param year Year.
   * @param month Month.
   * @param dayOfTheMonth Day of the month.
   * @return An instance of date value.
   */
  public static DateValue of(int year, int month, int dayOfTheMonth) {
    return new DateValue(year, month, dayOfTheMonth);
  }

  /**
   * Makes an instance of date value from a {@code String} in format {@code yyyy-mm-dd}.
   * 
   * @param date Date in {@code String} format ({@code yyyy-mm-dd}).
   * @throws NullPointerException If {@code date} is {@code null}.
   * @throws ParseException If {@code date} don't respect the {@code yyyy-mm-dd} format.
   * @return An instance of date value.
   */
  public static DateValue parse(String date) throws ParseException {
    return new DateValue(date);
  }

  /**
   * Constructor.
   * 
   * @param year Year.
   * @param month Month.
   * @param dayOfTheMonth Day of the month.
   */
  public DateValue(int year, int month, int dayOfTheMonth) {
    this.dayOfTheMonth = dayOfTheMonth;
    this.month = month;
    this.year = year;
  }

  /**
   * Constructor.
   * 
   * @param date Date in {@code String} format ({@code yyyy-mm-dd}).
   * @throws NullPointerException If {@code date} is {@code null}.
   * @throws ParseException If {@code date} don't respect the {@code yyyy-mm-dd} format.
   */
  public DateValue(String date) throws ParseException {
    if (date == null) {
      throw new NullPointerException("date should be not null");
    }

    if (date.matches(DATE_FORMAT)) {
      year = Integer.parseInt(date.substring(0, 4));
      month = Integer.parseInt(date.substring(5, 7));
      dayOfTheMonth = Integer.parseInt(date.substring(8));
    } else {
      throw new ParseException("date must respect yyyy-mm-dd format", date.length());
    }
  }

  /**
   * Returns the day of the month.
   * 
   * @return The day of the month.
   */
  public int getDayOfTheMonth() {
    return dayOfTheMonth;
  }

  /**
   * Returns the month.
   * 
   * @return The month.
   */
  public int getMonth() {
    return month;
  }

  /**
   * Returns the year.
   * 
   * @return The year.
   */
  public int getYear() {
    return year;
  }
}