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

    List<Album> findByArtistContainingIgnoreCaseAndTitleContainingIgnoreCase(String artist, String title);

    List<Album> findByReleaseDateGreaterThan(Date releaseDate);

    List<Album> findByReleaseDateLessThan(Date releaseDate);

    List<Album> findByTrackCountLessThan(int trackCount);

    List<Album> findByTrackCountGreaterThan(int trackCount);

}
