package labb1music.repository;

import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;

import labb1music.entity.Album;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {

    @Find
    List<Album> findAlbumById(Long id);

    @Find
    Album existsById(Long id);

    @Find
    List<Album> filterById(Long id);

    @Find
    List<Album> filterByTitle(String title);

    @Find
    List<Album> filterByArtist(String artist);

    @Find
    List<Album> filterByGenre(String genre);

    @Find
    List<Album> filterByReleaseYearAfter(Integer releaseYear);

    @Find
    List<Album> filterByReleaseYearBefore(Integer releaseYear);

    @Find
    Page<Album> findAll(PageRequest pageable);

    @Find
    Optional<Album> findByTitleContainingIgnoreCase(String title);

}

