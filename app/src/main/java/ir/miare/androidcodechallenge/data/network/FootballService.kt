package ir.miare.androidcodechallenge.data.network

import ir.logicbase.mockfit.Mock
import ir.miare.androidcodechallenge.data.network.model.PlayersAndLeague
import retrofit2.http.GET

interface FootballService {
    @Mock("data.json")
    @GET("list")
    suspend fun getData(): List<PlayersAndLeague>
}