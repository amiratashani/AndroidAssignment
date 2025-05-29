package ir.miare.androidcodechallenge.ui.ranking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.miare.androidcodechallenge.domain.usecase.SortFlag

@Composable
fun SortingRadioButton(
    selectedFlag: SortFlag,
    onSelected: (SortFlag) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.secondaryContainer) // Replace with indigo_200 if custom
            .padding(vertical = 16.dp)
    )
    {
        Text(
            text = "Sorting by: ",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            SortFlag.entries.forEach {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelected(it) }
                        .padding(vertical = 4.dp)
                ) {
                    RadioButton(
                        selected = selectedFlag == it,
                        onClick = { onSelected(it) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(
                        text = getSortRadioTitle(it),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SortingRadioButtonPreview() {
    SortingRadioButton(SortFlag.NONE) {}
}


fun getSortRadioTitle(sortFlag: SortFlag): String {
    return when (sortFlag) {
        SortFlag.NONE -> "None"
        SortFlag.RANK -> "Team & league ranking"
        SortFlag.GOALS -> "Most goals scored by a player"
        SortFlag.AVERAGE_GOAL -> "Average goal per match in a league"
    }
}