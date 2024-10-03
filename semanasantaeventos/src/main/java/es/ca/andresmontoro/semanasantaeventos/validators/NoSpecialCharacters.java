package es.ca.andresmontoro.semanasantaeventos.validators;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoSpecialCharactersValidator.class)
public @interface NoSpecialCharacters {
  String message() default "No se permiten caracteres especiales";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
