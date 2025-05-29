package ir.miare.androidcodechallenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ir.miare.androidcodechallenge.ui.ranking.RankingScreen
import ir.miare.androidcodechallenge.ui.theme.AppTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                RankingScreen()
            }
        }
    }
}
