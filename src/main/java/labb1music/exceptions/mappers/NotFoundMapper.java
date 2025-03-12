package labb1music.exceptions.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import labb1music.exceptions.NotFound;

@Provider
public class NotFoundMapper implements ExceptionMapper<NotFound> {

    @Override
    public Response toResponse(NotFound notFound) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(notFound.getMessage())
                .build();
    }

}
