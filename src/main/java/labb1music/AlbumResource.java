package labb1music;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import labb1music.business.AlbumService;
import labb1music.dto.AlbumResponse;
import labb1music.dto.CreateAlbum;
import labb1music.dto.UpdateAlbum;
import labb1music.entity.Album;
import labb1music.exceptions.InvalidRequest;
import lombok.extern.java.Log;

import java.util.List;

import static jakarta.ws.rs.core.Response.Status.CREATED;

@Path("albums")
@Log
public class AlbumResource {

    private AlbumService albumService;

    @Inject
    public AlbumResource(AlbumService albumService) {
        this.albumService = albumService;
    }

    public AlbumResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AlbumResponse> getAllAlbums() {
        return albumService.getAllAlbums()
                .stream()
                .map(AlbumResponse::new)
                .toList();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AlbumResponse getOneAlbum(@PathParam("id") @Positive Long id) {
        if (id == null) throw new InvalidRequest("Id cannot be null");
        return albumService.getAlbumById(id);
    }

    @POST
    @Transactional
    public Response createAlbum(@Valid CreateAlbum createAlbum) {
        Album album = albumService.createAlbum(createAlbum);
        return Response.status(CREATED).entity(AlbumResponse.map(album)).build();

    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateAlbum(@PathParam("id") @Positive Long id, @Valid UpdateAlbum updateAlbum) {
        albumService.updateAlbum(id, updateAlbum);
        return Response.ok("Album updated successfully!").build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteAlbum(@PathParam("id") @Positive Long id) {
        albumService.deleteAlbum(id);
        return Response.ok("Album deleted successfully!").build();
    }

}
