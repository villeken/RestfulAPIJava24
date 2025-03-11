package labb1music.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import labb1music.rules.ValidAlbum;

@ValidAlbum(message = "Not the default message")
public record CreateAlbum(@NotBlank @NotNull (message = "Title is mandatory") String title,
                          @NotBlank @NotNull (message = "Artist is mandatory") String artist,
                          @Positive @NotBlank @NotNull (message = "Release year is mandatory and can not be less than 0") Integer releaseYear,
                          @Positive @NotBlank @NotNull (message = "Track count is mandatory and can not be less than 0") Integer trackCount,
                          @NotBlank @NotNull (message = "Genre is mandatory") String genre) {

}
