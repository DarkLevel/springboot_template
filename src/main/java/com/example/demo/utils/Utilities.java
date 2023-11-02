package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {

  public static String formatDateToISO(Date date) {
    return formatDate("yyyy-MM-dd'T'HH:mm:ss.sssXXX", date);
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

}
