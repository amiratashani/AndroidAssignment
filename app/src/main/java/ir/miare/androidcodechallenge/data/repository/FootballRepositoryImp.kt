package ir.miare.androidcodechallenge.data.repository

import ir.miare.androidcodechallenge.IoDispatcher
import ir.miare.androidcodechallenge.data.network.FootballService
import ir.miare.androidcodechallenge.data.network.model.PlayersAndLeague
import ir.miare.androidcodechallenge.domain.repository.FootballRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FootballRepositoryImp @Inject constructor(
    private val footballService: FootballService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FootballRepository {
    override suspend fun getData(): List<PlayersAndLeague> {
        return withContext(ioDispatcher) {
            footballService.getData()
        }
    }
}