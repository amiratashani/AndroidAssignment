package ir.miare.androidcodechallenge.domain.usecase

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.mockk.coEvery
import io.mockk.mockk
import ir.miare.androidcodechallenge.data.network.model.PlayersAndLeague
import ir.miare.androidcodechallenge.domain.repository.FootballRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class GetDataSortedByUseCaseTest {

    private lateinit var mockRepository: FootballRepository
    private lateinit var testDispatcher: TestDispatcher
    private lateinit var getDataSortedByUseCase: GetDataSortedByUseCase
    private val fakeData: List<PlayersAndLeague> = jacksonObjectMapper().readValue(FAKE_DATA)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)

        mockRepository = mockk()
        coEvery { mockRepository.getData() } returns fakeData

        getDataSortedByUseCase = GetDataSortedByUseCase(mockRepository, testDispatcher)
    }

    @Test
    fun `NONE sort returns original data`() = runTest {
        val result = getDataSortedByUseCase(SortFlag.NONE)

        assertEquals(fakeData, result)
    }

    @Test
    fun `RANK sort sorts by league rank and player team rank`() = runTest {
        val result = getDataSortedByUseCase(SortFlag.RANK)

        assertEquals("Premier League", result[0].league.name)
        assertEquals("Man City", result[0].players[0].team.name)
    }

    @Test
    fun `GOALS sort sorts players by total goals descending`() = runTest {
        val result = getDataSortedByUseCase(SortFlag.GOALS)

        assertEquals("Edin Dzeko", result[0].players[0].name)
        assertEquals("Mohammad Salah", result[1].players[1].name)
    }

    @Test
    fun `AVERAGE_GOAL sort sorts leagues by avg goals per match descending`() = runTest {
        val result = getDataSortedByUseCase(SortFlag.AVERAGE_GOAL)

        assertEquals("Premier League", result[0].league.name)
        assertEquals("Antoine Griezmann", result[1].players[0].name)
    }


}