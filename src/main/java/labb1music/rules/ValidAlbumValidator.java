package labb1music.rules;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import labb1music.dto.AlbumResponse;

public class ValidAlbumValidator implements ConstraintValidator<ValidAlbum, AlbumResponse> {

    @Override
    public boolean isValid(AlbumResponse album, ConstraintValidatorContext context) {
        if (album.title() != null && Character.isUpperCase(album.title().charAt(0)) &&
                album.releaseYear() >= 1900 && album.releaseYear() <= 2100) {
            return true;
        }

        context.disableDefaultConstraintViolation();

        if (album.title() == null || !Character.isUpperCase(album.title().charAt(0))) {
            context.buildConstraintViolationWithTemplate("Title must start with an uppercase letter")
                    .addPropertyNode("title")
                    .addConstraintViolation();
        }

        if (album.releaseYear() < 1900 || album.releaseYear() > 2100) {
            context.buildConstraintViolationWithTemplate("Release year must be between 1900 and 2100")
                    .addPropertyNode("releaseYear")
                    .addConstraintViolation();
        }

        return false;
    }
}
