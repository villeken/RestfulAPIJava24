package labb1music;

import labb1music.business.AlbumService;
import labb1music.dto.AlbumResponse;
import labb1music.dto.CreateAlbum;
import labb1music.dto.UpdateAlbum;
import labb1music.entity.Album;
import labb1music.exceptions.NotFound;
import labb1music.mapper.AlbumMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlbumServiceTest {

    @Mock
    private AlbumRepository repository;

    @InjectMocks
    private AlbumService albumService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAlbums() {

        Album album = new Album();
        album.setId(1L);
        album.setTitle("Test Album");

        when(repository.findAll()).thenReturn(Stream.of(album));

        List<AlbumResponse> albums = albumService.getAllAlbums();

        assertNotNull(albums);
        assertEquals(1, albums.size());
        assertEquals("Test Album", albums.getFirst().title());
    }

    @Test
    void testGetBookById() {
        Album album = new Album();
        album.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(album));

        AlbumResponse response = albumService.getBookById(1L);

        assertNotNull(response);
        assertEquals(1L, response.id());
    }

    @Test
    void testGetBookByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFound.class, () -> albumService.getBookById(1L));
    }

    @Test
    void testCreateAlbum() {
        CreateAlbum createAlbum = new CreateAlbum("Title", "Artist", 2023, 10, "Genre");
        Album album = AlbumMapper.map(createAlbum);
        when(repository.save(any(Album.class))).thenReturn(album);

        Album newAlbum = albumService.createAlbum(createAlbum);

        assertNotNull(newAlbum);
        assertEquals("Title", newAlbum.getTitle());
    }

    @Test
    void testUpdateAlbum() {
        Album album = new Album();
        album.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(album));

        UpdateAlbum updateAlbum = new UpdateAlbum(1L, "New Title", "New Artist", "New Genre", 12, 2024);
        albumService.updateAlbum(1L, updateAlbum);

        verify(repository).save(album);
        assertEquals("New Title", album.getTitle());
    }

    @Test
    void testUpdateAlbumNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        UpdateAlbum updateAlbum = new UpdateAlbum(1L, "New Title", "New Artist", "New Genre", 12, 2024);

        assertThrows(NotFound.class, () -> albumService.updateAlbum(1L, updateAlbum));
    }
}
