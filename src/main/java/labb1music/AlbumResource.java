package labb1music;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import labb1music.business.AlbumService;
import labb1music.dto.AlbumResponse;
import labb1music.entity.Album;
import lombok.extern.java.Log;

import java.util.List;
import java.util.stream.Collectors;

@Path("albums")
@Log
public class AlbumResource {

    private final AlbumService albumService;

    @Inject
    public AlbumResource(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AlbumResponse> getAllAlbums() {
        return albumService.getAllAlbums()
                .stream()
                .map(AlbumResponse::new)
                .toList();
    }
}
