package model.response

import com.fasterxml.jackson.annotation.JsonProperty
import model.Team

data class PlayerResponse(
    val data: List<Player>? = null,
    val meta: Meta? = null
)

data class Player(
    val id: Long,
    @field:JsonProperty("first_name")
    val firstName: String? = null,
    @field:JsonProperty("height_feet")
    val heightFeet: Long? = null,
    @field:JsonProperty("height_inches")
    val heightInches: Long? = null,
    @field:JsonProperty("last_name")
    val lastName: String? = null,
    val position: String? = null,
    @field:JsonProperty("weight_pounds")
    val weightPounds: Long? = null,
    val team: Team? = null
)

data class Meta(
    @field:JsonProperty("next_page")
    val nextPage: Long? = null,
)