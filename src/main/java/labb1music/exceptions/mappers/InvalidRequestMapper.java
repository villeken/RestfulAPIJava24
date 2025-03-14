package labb1music.exceptions.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import labb1music.exceptions.InvalidRequest;

@Provider
public class InvalidRequestMapper implements ExceptionMapper<InvalidRequest> {

    @Override
    public Response toResponse(InvalidRequest InvalidRequest) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(InvalidRequest.getMessage())
                .build();
    }
}
