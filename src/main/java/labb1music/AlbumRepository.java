package labb1music;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

import jakarta.enterprise.context.ApplicationScoped;
import labb1music.entity.Album;

import java.util.List;

@Repository
@ApplicationScoped
public interface AlbumRepository extends CrudRepository<Album, Long> {

    List<Album> findByArtistContainingIgnoreCase(String artist);

    List<Album> findByGenreContainingIgnoreCase(String genre);

    List<Album> findByTitleContainingIgnoreCase(String title);

    List<Album> findByReleaseYearAfter(Integer releaseYear);

    List<Album> findByReleaseYearBefore(Integer releaseYear);
}

