package repository

import io.quarkus.mongodb.panache.kotlin.reactive.ReactivePanacheMongoRepository
import model.mongo.TeamMongo
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TeamRepository : ReactivePanacheMongoRepository<TeamMongo> {
    fun findById(id: Long) = find("id", id).firstResult()
    fun findByAbbreviation(abbreviation: String) = find("abbreviation", abbreviation).stream()
}