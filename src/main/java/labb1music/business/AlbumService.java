package labb1music.business;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import labb1music.AlbumRepository;
import labb1music.dto.AlbumResponse;
import labb1music.dto.CreateAlbum;
import labb1music.entity.Album;
import labb1music.exceptions.NotFound;
import labb1music.mapper.AlbumMapper;


import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class AlbumService {

    private AlbumRepository repository;

    @Inject
    public AlbumService(AlbumRepository bookRepository) {
        this.repository = bookRepository;

    }

    public AlbumService() {
    }

    public List<AlbumResponse> getAllAlbums() {
        return repository.findAll()
                .map(AlbumResponse::new)
                .filter(Objects::nonNull)
                .toList();

    }

    public AlbumResponse getBookById(Long id) {
        return repository.findById(id)
                .map(AlbumResponse::new)
                .orElseThrow(
                        () -> new NotFound("Album with id " + id + " not found!")
                );
    }
    
    public Album createAlbum (CreateAlbum album) {
        Album newAlbum = AlbumMapper.map(album);
        newAlbum = repository.save(newAlbum);
        return newAlbum;
    }

}
