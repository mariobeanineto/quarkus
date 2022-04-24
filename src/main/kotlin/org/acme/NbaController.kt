package org.acme

import org.jboss.resteasy.annotations.jaxrs.PathParam
import service.ReactiveNbaService
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/nba/teams")
class NbaController(
    val nbaService: ReactiveNbaService
) {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getNbaTeams(@PathParam id: Long) = nbaService.getNbaTeam(id)

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllNbaTeams() = nbaService.getAllNbaTeams()
}