package ir.miare.androidcodechallenge.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface FootballService {
    @GET("upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
    )
}