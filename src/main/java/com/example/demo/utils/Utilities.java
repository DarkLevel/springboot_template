package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utilities {

  private Utilities() {

  }

  public static String formatDateToISO(Date date) {
    return formatDate("yyyy-MM-dd'T'HH:mm:ss.sssXXX", date);
  }

  public static String formatDateToISOWithoutMillis(Date date) {
    return formatDate("yyyy-MM-dd'T'HH:mm:ssXXX", date);
  }

  public static String formatDate(String format, Date date) {
    String formattedDate;

    try {
      formattedDate = new SimpleDateFormat(format).format(date);
    } catch (Exception e) {
      formattedDate = null;
    }

    return formattedDate;
  }

  public static boolean startsWith(String object, List<String> list) {
    return list.stream().anyMatch(object::startsWith);
  }

}
