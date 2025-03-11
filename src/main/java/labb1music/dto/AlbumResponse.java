package labb1music.dto;

import labb1music.entity.Album;

public record AlbumResponse(Long id, String title, String artist, String genre, Integer trackCount, Integer releaseYear) {

    public AlbumResponse(Album album){
        this(album.getId(), album.getTitle(), album.getArtist(), album.getGenre(), album.getTrackCount(), album.getReleaseYear());
    }

}
