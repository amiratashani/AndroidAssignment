package ir.miare.androidcodechallenge.domain.usecase

import ir.miare.androidcodechallenge.IoDispatcher
import ir.miare.androidcodechallenge.data.network.model.PlayersAndLeague
import ir.miare.androidcodechallenge.domain.repository.FootballRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetDataSortedByUseCase @Inject constructor(
    private val footballRepository: FootballRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(sortFlag: SortFlag): List<PlayersAndLeague> {
        return withContext(dispatcher) {
            val leagueAndPlayers = footballRepository.getData()

            when (sortFlag) {
                SortFlag.NONE -> leagueAndPlayers
                SortFlag.RANK -> sortByRank(leagueAndPlayers)
                SortFlag.GOALS -> sortByMostGoal(leagueAndPlayers)
                SortFlag.AVERAGE_GOAL -> sortByAverageGoal(leagueAndPlayers)
            }
        }
    }


    private fun sortByRank(fakeData: List<PlayersAndLeague>): List<PlayersAndLeague> {
        val sortedList = fakeData.sortedBy { it.league.rank }
            .map { data ->
                val sortedPlayers = data.players.sortedBy { it.team.rank }
                PlayersAndLeague(data.league, sortedPlayers)
            }

        return sortedList
    }

    private fun sortByMostGoal(fakeData: List<PlayersAndLeague>): List<PlayersAndLeague> {
        val sortedList = fakeData
            .map { data ->
                val sortedPlayers = data.players.sortedByDescending { it.totalGoal }
                PlayersAndLeague(data.league, sortedPlayers)
            }
        return sortedList
    }

    private fun sortByAverageGoal(fakeData: List<PlayersAndLeague>): List<PlayersAndLeague> {
        val sortedList = fakeData
            .map { leaguePlayers ->
                val totalGoals = leaguePlayers.players.sumOf { it.totalGoal }
                val totalMatches = leaguePlayers.league.totalMatches
                val avgGoalsPerMatch = if (totalMatches > 0) {
                    totalGoals.toFloat() / totalMatches.toFloat()
                } else 0f

                leaguePlayers to avgGoalsPerMatch
            }
            .sortedByDescending { it.second }
            .map { it.first }

        return sortedList
    }
}

enum class SortFlag {
    NONE, RANK, GOALS, AVERAGE_GOAL
}