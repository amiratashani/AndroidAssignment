package ir.miare.androidcodechallenge.domain.repository

import ir.miare.androidcodechallenge.data.network.model.PlayersAndLeague

interface FootballRepository {
    suspend fun getData(): List<PlayersAndLeague>
}