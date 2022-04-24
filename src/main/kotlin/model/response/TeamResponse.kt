package model

import com.fasterxml.jackson.annotation.JsonProperty
import io.quarkus.mongodb.panache.common.MongoEntity


data class TeamResponse(
    val data: List<Team>? = null
)

@MongoEntity(collection = "teams")
data class Team(
    val id: Long,
    val abbreviation: String? = null,
    val city: String? = null,
    val conference: String? = null,
    val division: String? = null,
    @field:JsonProperty("full_name")
    val fullName: String? = null,
    val name: String? = null
)
