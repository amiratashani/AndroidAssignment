package ir.miare.androidcodechallenge.data.network.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable


data class PlayersAndLeague(
    @JsonProperty("league") val league: League,
    @JsonProperty("players") val players: List<Player>
)

data class League(
    @JsonProperty("name") val name: String,
    @JsonProperty("country") val country: String,
    @JsonProperty("rank") val rank: Int,
    @JsonProperty("total_matches") val totalMatches: Int,
)

data class Player(
    @JsonProperty("name") val name: String,
    @JsonProperty("team") val team: Team,
    @JsonProperty("total_goal") val totalGoal: Int
) : Serializable

data class Team(
    @JsonProperty("name") val name: String,
    @JsonProperty("rank") val rank: Int
) : Serializable
