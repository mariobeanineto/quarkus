package config

import client.NbaClient
import io.quarkus.runtime.Startup
import model.mongo.PlayerMongo
import model.mongo.TeamMongo
import org.eclipse.microprofile.rest.client.inject.RestClient
import repository.PlayerRepository
import repository.TeamRepository

@Startup
class PopulateDbConfig(
    @RestClient
    val nbaClient: NbaClient,
    val teamRepository: TeamRepository,
    val playerRepository: PlayerRepository
) {

    companion object {
        private const val PER_PAGE: Long = 100L
    }

    init {
        populateDB()
    }

    private fun populateDB() {
        val teamMongoList = populateTeams()
        populatePlayers(teamMongoList)
    }

    private fun populateTeams(): MutableList<TeamMongo> {
        val teamMongoList = mutableListOf<TeamMongo>()
        nbaClient.getAllNbaTeams().data?.forEach {
            val teamMongo = TeamMongo()
            teamMongo.id = it.id
            teamMongo.abbreviation = it.abbreviation.toString()
            teamMongo.city = it.city.toString()
            teamMongo.conference = it.conference.toString()
            teamMongo.division = it.division.toString()
            teamMongo.fullName = it.fullName.toString()
            teamMongo.name = it.name.toString()
            teamMongoList.add(teamMongo)
        }
        teamRepository.persist(teamMongoList.stream()).subscribe().with {
            println("complete team insertion")
        }
        return teamMongoList
    }

    private fun populatePlayers(teamMongoList: MutableList<TeamMongo>, page: Long = 1L) {
        val playersList = mutableListOf<PlayerMongo>()
        val playerResponse = nbaClient.getAllNbaPlayers(PER_PAGE, page)
        playerResponse.data?.forEach {
            val playerMongo = PlayerMongo()
            playerMongo.id = it.id
            playerMongo.firstName = it.firstName.toString()
            playerMongo.heightFeet = it.heightFeet
            playerMongo.heightInches = it.heightInches
            playerMongo.lastName = it.lastName.toString()
            playerMongo.position = it.position.toString()
            playerMongo.weightPounds = it.weightPounds
            playerMongo.team = getTeamMongo(teamMongoList, it.team!!.id)
            playersList.add(playerMongo)
        }
        playerRepository.persist(playersList.stream()).subscribe().with {
            if (playerResponse.meta?.nextPage != null) {
                val nextPage = page + 1L
                populatePlayers(teamMongoList, nextPage)
            } else {
                println("finished populatePlayers")
            }
        }
    }

    private fun getTeamMongo(teamMongoList: MutableList<TeamMongo>, id: Long): TeamMongo {
        return teamMongoList.first { it.id == id }
    }
}