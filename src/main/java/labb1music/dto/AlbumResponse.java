package labb1music.dto;

import labb1music.entity.Album;

public record AlbumResponse(Long id, String Title, String Artist, String Genre, Integer trackCount) {

    public AlbumResponse(Album album){
        this(album.getId(), album.getTitle(), album.getArtist(), album.getGenre(), album.getTrackCount());
    }

}
