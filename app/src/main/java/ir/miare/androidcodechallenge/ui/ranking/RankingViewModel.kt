package ir.miare.androidcodechallenge.ui.ranking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.miare.androidcodechallenge.data.network.model.Player
import ir.miare.androidcodechallenge.data.network.model.PlayersAndLeague
import ir.miare.androidcodechallenge.domain.usecase.GetDataSortedByUseCase
import ir.miare.androidcodechallenge.domain.usecase.SortFlag
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RankingViewModel @Inject constructor(
    private val getDataSortedByUseCase: GetDataSortedByUseCase
) : ViewModel() {

    private val _rankingTable = MutableStateFlow(RankingData())
    val rankingTable: StateFlow<RankingData>
        get() = _rankingTable

    init {
        getDataSortedBy(SortFlag.NONE)
    }

    fun getDataSortedBy(sortFlag: SortFlag) {
        _rankingTable.update {
            it.copy(isLoading = true, selected = sortFlag)
        }
        viewModelScope.launch {
            val data = getDataSortedByUseCase(sortFlag)
            _rankingTable.update {
                it.copy(isLoading = false, data = data)
            }
        }
    }

    fun setPlayer(player: Player){
        _rankingTable.update {
            it.copy(selectedPlayer = player)
        }
    }

    fun resetBottomSheet() {
        _rankingTable.update {
            it.copy(selectedPlayer = null)
        }
    }
}


data class RankingData(
    val isLoading: Boolean = true,
    val selected: SortFlag = SortFlag.NONE,
    val data: List<PlayersAndLeague> = emptyList(),
    val selectedPlayer: Player? = null
)