package com.mutants.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

@Target({ FIELD,ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = LengthValidation.class)
@Documented
public @interface Length {
    String message() default "El elemento no cumple las dimensiones";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
