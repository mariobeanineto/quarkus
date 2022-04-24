package repository

import io.quarkus.mongodb.panache.kotlin.reactive.ReactivePanacheMongoRepository
import model.mongo.PlayerMongo
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PlayerRepository : ReactivePanacheMongoRepository<PlayerMongo> {
    fun findById(id: Long) = find("id", id).firstResult()
}