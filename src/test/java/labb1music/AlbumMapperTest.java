package labb1music;

import labb1music.dto.AlbumResponse;
import labb1music.dto.CreateAlbum;
import labb1music.entity.Album;
import labb1music.mapper.AlbumMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlbumMapperTest {

    @Test
    public void TestMapAlbumToAlbumResponse() {

        Album album = new Album();
        album.setId(1L);
        album.setTitle("Test Album");
        album.setArtist("Test Artist");
        album.setGenre("Rock");
        album.setTrackCount(12);
        album.setReleaseYear(2020);

        AlbumResponse response = AlbumMapper.map(album);

        assertNotNull(response);
        assertEquals(album.getId(), response.id());
        assertEquals(album.getTitle(), response.title());
        assertEquals(album.getArtist(), response.artist());
        assertEquals(album.getGenre(), response.genre());
        assertEquals(album.getTrackCount(), response.trackCount());
        assertEquals(album.getReleaseYear(), response.releaseYear());
    }

    @Test
    public void TestMapValidCreateAlbumToAlbum() {

        CreateAlbum createAlbum = new CreateAlbum(
                "Test Album",
                "Test Artist",
                2020,
                12,
                "Rock"
        );

        Album result = AlbumMapper.map(createAlbum);

        assertNotNull(result);
        assertEquals(createAlbum.title(), result.getTitle());
        assertEquals(createAlbum.artist(), result.getArtist());
        assertEquals(createAlbum.releaseYear(), result.getReleaseYear());
        assertEquals(createAlbum.trackCount(), result.getTrackCount());
        assertEquals(createAlbum.genre(), result.getGenre());
    }

    @Test
    public void TestMapNullAlbumReturnsNull() {

        Album album = null;

        AlbumResponse response = AlbumMapper.map(album);

        assertNull(response);
    }
}
