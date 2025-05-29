package ir.miare.androidcodechallenge.ui.ranking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.miare.androidcodechallenge.data.network.model.League
import ir.miare.androidcodechallenge.data.network.model.Player
import ir.miare.androidcodechallenge.data.network.model.PlayersAndLeague
import ir.miare.androidcodechallenge.data.network.model.Team


@Composable
fun RankList(isLoading: Boolean, data: List<PlayersAndLeague>, onPlayerClick: (Player) -> Unit) {
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.Center),
                color = Color.DarkGray
            )
        }
    } else {
        LazyColumn {
            items(data) {
                RankItem(it.league, it.players, onPlayerClick)
            }
        }
    }
}

@Composable
fun RankItem(league: League, players: List<Player>, onPlayerClick: (Player) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        LeagueHeader(league)
        players.forEach {
            PlayerItem(it, onPlayerClick)
        }
    }
}

@Composable
fun PlayerItem(player: Player, onPlayerClick: (Player) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp)
            .clickable { onPlayerClick(player) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp)

        ) {
            Text(
                text = player.team.rank.toString(),
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(end = 16.dp)
            )

            Column {
                Text(
                    text = player.name,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = player.team.name,
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
            }
        }

        Divider(
            color = Color(0xFFDDDDDD),
            thickness = 1.dp,
            modifier = Modifier
                .padding(top = 16.dp)
        )
    }
}

@Composable
fun LeagueHeader(league: League) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFDDDDDD)) // Background color #DDDDDD
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = league.name,
                fontSize = 18.sp,
                color = Color(0xFF6E6E6E)
            )
            Text(
                text = " - ",
                fontSize = 18.sp,
                color = Color(0xFF6E6E6E)
            )
            Text(
                text = league.country,
                fontSize = 18.sp,
                color = Color(0xFF6E6E6E)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PlayerItemPreview() {
    PlayerItem(Player(name = "Salah", totalGoal = 25, team = Team(name = "Liverpool", rank = 2))) {}
}

@Composable
@Preview(showBackground = true)
fun LeagueHeaderPreview() {
    LeagueHeader(League(name = "Premier League", country = "England", rank = 1, totalMatches = 25))
}

@Composable
@Preview(showBackground = true)
fun RankListPreview() {
    val league = League("Premier League", "England", 0, 0)
    val players = listOf(
        Player("Mohammad Salah", Team(name = "Liverpool", rank = 1), 15),
        Player("Erling Haaland", Team(name = "Man City", rank = 2), 25),
        Player("Marcus Rashford", Team(name = "Man United", rank = 3), 35)
    )
    RankItem(league, players) {}
}

