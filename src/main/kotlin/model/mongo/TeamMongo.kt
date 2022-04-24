package model.mongo

import io.quarkus.mongodb.panache.common.MongoEntity
import org.bson.codecs.pojo.annotations.BsonId

@MongoEntity(collection = "teams")
class TeamMongo {
    @BsonId
    var id: Long? = null
    lateinit var abbreviation: String
    lateinit var city: String
    lateinit var conference: String
    lateinit var division: String
    lateinit var fullName: String
    lateinit var name: String
}