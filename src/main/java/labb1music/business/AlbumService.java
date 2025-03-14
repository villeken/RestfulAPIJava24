package labb1music.business;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import labb1music.AlbumRepository;
import labb1music.dto.AlbumResponse;
import labb1music.dto.CreateAlbum;
import labb1music.dto.UpdateAlbum;
import labb1music.entity.Album;
import labb1music.exceptions.NotFound;
import labb1music.mapper.AlbumMapper;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AlbumService {

    private final AlbumRepository repository;

    @Inject
    public AlbumService(AlbumRepository albumRepository) {
        this.repository = albumRepository;
    }

    public List<Album> getAllAlbums() {
        return repository.findAll().collect(Collectors.toList());
    }

    public AlbumResponse getAlbumById(Long id) {
        return repository.findById(id)
                .map(AlbumResponse::new)
                .orElseThrow(() -> new NotFound("Album with id " + id + " not found!"));
    }

    public Album createAlbum(CreateAlbum album) {
        Album newAlbum = AlbumMapper.map(album);
        return repository.save(newAlbum);
    }

    public void updateAlbum(Long id, UpdateAlbum album) {
        var oldAlbum = repository.findById(id).orElseThrow(() -> new NotFound("Album with id " + id + " not found!"));
        if (album.title() != null)
            oldAlbum.setTitle(album.title());
        if (album.artist() != null)
            oldAlbum.setArtist(album.artist());
        if (album.genre() != null)
            oldAlbum.setGenre(album.genre());
        if (album.trackCount() != 0)
            oldAlbum.setTrackCount(album.trackCount());
        if (album.releaseYear() != 0)
            oldAlbum.setReleaseYear(album.releaseYear());
        repository.save(oldAlbum);
    }
}
