package ir.miare.androidcodechallenge.ui.ranking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RankingScreen(rankingViewModel: RankingViewModel = hiltViewModel()) {
    Surface {
        val rankTable = rankingViewModel.rankingTable.collectAsState()

        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
            SortingRadioButton(selectedFlag = rankTable.value.selected, onSelected = {
                rankingViewModel.getDataSortedBy(it)
            })
            RankList(rankTable.value.isLoading,rankTable.value.data, onPlayerClick = { rankingViewModel.setPlayer(it) })
        }


        BottomSheetHost(
            rankTable.value.selectedPlayer,
            onDismiss = { rankingViewModel.resetBottomSheet() }
        )
    }
}