package labb1music.business;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import labb1music.AlbumRepository;
import labb1music.dto.AlbumResponse;


import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class AlbumService {

    private AlbumRepository repository;

    @Inject
    public AlbumService(AlbumRepository bookRepository) {
        this.repository = bookRepository;

    }

    public AlbumService() {}

    public List<AlbumResponse> getAllAlbums(){
        return repository.findAll()
                .map(AlbumResponse::new)
                .filter(Objects::nonNull)
                .toList();

    }

}
