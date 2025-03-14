package labb1music.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import labb1music.entity.Album;

public record AlbumResponse(@NotNull @Positive Long id,
                            @NotBlank String title,
                            @NotBlank String artist,
                            @NotBlank String genre,
                            @NotNull Integer trackCount,
                            @NotNull Integer releaseYear) {

    public AlbumResponse(Album album) {
        this(album.getId(), album.getTitle(), album.getArtist(), album.getGenre(), album.getTrackCount(), album.getReleaseYear());
    }

    public static AlbumResponse map(Album album) {
        return new AlbumResponse(album.getId(), album.getTitle(), album.getArtist(), album.getGenre(), album.getTrackCount(), album.getReleaseYear());
    }

}
