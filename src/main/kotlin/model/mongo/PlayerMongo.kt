package model.mongo

import io.quarkus.mongodb.panache.common.MongoEntity
import org.bson.codecs.pojo.annotations.BsonId

@MongoEntity(collection = "players")
class PlayerMongo {
    @BsonId
    var id: Long? = null
    lateinit var firstName: String
    var heightFeet: Long? = null
    var heightInches: Long? = null
    lateinit var lastName: String
    lateinit var position: String
    var weightPounds: Long? = null
    lateinit var team: TeamMongo
}