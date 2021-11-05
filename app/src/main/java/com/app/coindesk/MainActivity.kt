package com.app.coindesk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.app.coindesk.application.DeskApplication
import com.app.coindesk.entity.Coins
import com.app.coindesk.ui.theme.CoindeskTheme
import dagger.hilt.android.AndroidEntryPoint

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
                        val listOfCoins = it
                            ShowCoins(
                                listOfCoins
                            )
                    }
                }
            }
        })
    }
}

@Composable
fun ShowCoins(coins: List<Coins>) {
    Scaffold(modifier = Modifier.padding(0.dp), content = {
        TableComposable(coins)
    })
}

@Composable
fun TableComposable(coins: List<Coins>) {
    Scaffold(modifier = Modifier.padding(0.dp), content = {
        LazyColumn() {
            coins.forEach { coin ->
                item {
                    Text(modifier = Modifier.padding(5.dp), text = coin.chartName)
                    Text(modifier = Modifier.padding(5.dp), text = "eur: " + coin.bpi.eur.rate)
                    Text(modifier = Modifier.padding(5.dp), text = "gbp: " + coin.bpi.gbp.rate)
                    Text(modifier = Modifier.padding(5.dp), text = "usd: " + coin.bpi.usd.rate)
                    Text(modifier = Modifier.padding(5.dp), text = "updatedISO: " + coin.time.updatedISO)
                }
                item {
                    Divider(
                        color = Color.Black,
                        thickness = 1.dp
                    )
                }
            }
        }
    })
}
