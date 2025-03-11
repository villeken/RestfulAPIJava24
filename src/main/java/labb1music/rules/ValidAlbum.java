package labb1music.rules;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidAlbumValidator.class})
public @interface ValidAlbum {
    String message() default "Not a valid album";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
