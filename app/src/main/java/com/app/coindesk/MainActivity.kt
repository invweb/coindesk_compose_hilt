package com.app.coindesk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.LiveData
import com.app.coindesk.application.DeskApplication
import com.app.coindesk.entity.Coins
import com.app.coindesk.ui.theme.CoindeskTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.livedata.observeAsState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.saveCoinsToDB(this)

        val coinsList: LiveData<List<Coins>> =
            viewModel.observeDataIdDB(application as DeskApplication)
        coinsList.observe(this, {
            setContent {
                CoindeskTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(color = MaterialTheme.colors.background) {
                        Greeting(
                            this,
                            "Android" + it.size,
                            coinsList
                        )
                    }
                }
            }
        })
    }
}

@Composable
fun Greeting(name1: MainActivity, name: String, coinsList: LiveData<List<Coins>>) {
    Text(text = "Hello $name!")
    val launches: List<Coins?> by coinsList.observeAsState(listOf())

}
