package labb1music.mapper;

import labb1music.dto.AlbumResponse;
import labb1music.dto.CreateAlbum;
import labb1music.entity.Album;

public class AlbumMapper {

    public static AlbumResponse map(Album album) {
        if (album == null) {
            return null;
        }
        return new AlbumResponse(album.getId(), album.getTitle(), album.getArtist(), album.getGenre(), album.getTrackCount(), album.getReleaseYear());
    }

    public static Album map(CreateAlbum album) {
        if (album == null) {
            return null;
        }
        Album newAlbum = new Album();
        newAlbum.setTitle(album.title());
        newAlbum.setArtist(album.artist());
        newAlbum.setGenre(album.genre());
        newAlbum.setTrackCount(album.trackCount());
        newAlbum.setReleaseYear(album.releaseYear());
        return newAlbum;
    }

}
