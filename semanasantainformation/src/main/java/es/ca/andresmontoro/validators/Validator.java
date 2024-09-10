package es.ca.andresmontoro.validators;

import java.time.LocalDate;

public class Validator {
  private Validator() {
    throw new IllegalStateException("Utility class");
  }

  public static boolean isIdValid(Long id) {
    return id != null && id > 0;
  }

  public static boolean isBefore(LocalDate date1, LocalDate date2) {
    if(date1 == null || date2 == null) {
      return true;
    }
    else {
      return date1.isBefore(date2);
    }
  }
}
