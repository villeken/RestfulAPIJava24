package labb1music;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("albums")
public class AlbumResource {

    @GET
    public String firstTest(){
        return "First Test";
    }

}
