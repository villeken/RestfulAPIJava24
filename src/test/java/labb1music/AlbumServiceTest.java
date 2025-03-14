package labb1music;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlbumServiceTest {

    @Mock
    private AlbumRepository repository;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Album> query;

    @InjectMocks
    private AlbumService albumService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAlbumsAndReturnList() {

        AlbumRepository mockRepository = Mockito.mock(AlbumRepository.class);

        List<Album> expectedAlbums = List.of(
                new Album(),
                new Album()
        );

        Stream<Album> albumStream = expectedAlbums.stream();
        Mockito.when(mockRepository.findAll()).thenReturn(albumStream);

        AlbumService albumService = new AlbumService(mockRepository);

        List<Album> result = albumService.getAllAlbums();

        assertEquals(2, result.size());
        assertEquals(expectedAlbums, result);
        Mockito.verify(mockRepository).findAll();
    }

    @Test
    void testGetAlbumById() {
        Album album = new Album();
        album.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(album));

        AlbumResponse response = albumService.getAlbumById(1L);

        assertNotNull(response);
        assertEquals(1L, response.id());
    }

    @Test
    void testGetAlbumByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFound.class, () -> albumService.getAlbumById(1L));
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


    @Test
    public void test_delete_album_when_exists() {

        Long albumId = 1L;
        Album album = new Album();
        album.setId(albumId);
        album.setTitle("Test Album");
        album.setArtist("Test Artist");
        album.setGenre("Rock");
        album.setReleaseYear(2020);
        album.setTrackCount(10);

        AlbumRepository mockRepository = Mockito.mock(AlbumRepository.class);
        Mockito.when(mockRepository.findById(albumId)).thenReturn(Optional.of(album));

        AlbumService albumService = new AlbumService(mockRepository);

        albumService.deleteAlbum(albumId);

        Mockito.verify(mockRepository).findById(albumId);
        Mockito.verify(mockRepository).delete(album);
    }
}
