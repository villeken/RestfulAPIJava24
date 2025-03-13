package labb1music.business;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import labb1music.AlbumRepository;
import labb1music.dto.AlbumResponse;
import labb1music.dto.CreateAlbum;
import labb1music.dto.UpdateAlbum;
import labb1music.entity.Album;
import labb1music.exceptions.NotFound;
import labb1music.mapper.AlbumMapper;


import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class AlbumService {

    private AlbumRepository repository;

    @Inject
    public AlbumService(AlbumRepository albumRepository) {
        this.repository = albumRepository;

    }

    public AlbumService() {
    }

    @PersistenceContext
    private EntityManager em;

    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    public List<Album> getAllAlbums() {
        return em.createQuery("SELECT a FROM Album a", Album.class)
                .getResultList();
    }

    public AlbumResponse getAlbumById(Long id) {
        return repository.findById(id)
                .map(AlbumResponse::new)
                .orElseThrow(
                        () -> new NotFound("Album with id " + id + " not found!")
                );
    }

    public Album createAlbum(CreateAlbum album) {
        Album newAlbum = AlbumMapper.map(album);
        newAlbum = repository.save(newAlbum);
        return newAlbum;
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
