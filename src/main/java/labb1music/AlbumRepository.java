package labb1music;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;
import labb1music.entity.Album;

import java.util.Date;
import java.util.List;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {

    List<Album> findByArtistContainingIgnoreCase(String artist);

    List<Album> findByGenreContainingIgnoreCase(String genre);

    List<Album> findByTitleContainingIgnoreCase(String title);

    List<Album> findByReleaseYearAfter(Integer releaseYear);

    List<Album> findByReleaseYearBefore(Integer releaseYear);
}

