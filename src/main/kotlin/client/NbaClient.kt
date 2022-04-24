package client

import model.TeamResponse
import model.response.PlayerResponse
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam

@Path("api")
@RegisterRestClient
interface NbaClient {
    @GET
    @Path("v1/teams")
    fun getAllNbaTeams(): TeamResponse

    @GET
    @Path("v1/players")
    fun getAllNbaPlayers(@QueryParam("per_page") perPage: Long, @QueryParam("page") page: Long): PlayerResponse
}