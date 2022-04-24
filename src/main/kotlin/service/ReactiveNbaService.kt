package service

import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import model.mongo.TeamMongo
import repository.TeamRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ReactiveNbaService(
    val teamRepository: TeamRepository
) {

    fun getNbaTeam(id: Long): Uni<TeamMongo?> {
        return teamRepository.findById(id)
    }

    fun getAllNbaTeams(): Multi<TeamMongo> {
        return teamRepository.streamAll()
    }
}