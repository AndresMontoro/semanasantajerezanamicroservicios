package es.ca.andresmontoro.semanasantaeventos.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoSpecialCharactersValidator implements ConstraintValidator<NoSpecialCharacters, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if(value == null)
      return true;  // @NotNull will handle this

    String regex = "^[a-zA-ZÀ-ÿñÑ ]+$";
    return value.matches(regex);
  }
}
