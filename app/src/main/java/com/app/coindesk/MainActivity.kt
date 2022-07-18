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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.coindesk.application.DeskApplication
import com.app.coindesk.entity.Coins
import com.app.coindesk.ui.theme.CoindeskTheme
import dagger.hilt.android.AndroidEntryPoint

private var rootCoins: Coins? = null

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.saveCoinsToDB(this)

        val coinsList: LiveData<List<Coins>> =
            viewModel.observeDataIdDB(application as DeskApplication)

        coinsList.observe(this) {
            setContent {
                val navController = rememberNavController()
                CoindeskTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(color = MaterialTheme.colors.background) {
                        val listOfCoins = it

                        NavHost(
                            navController = navController,
                            startDestination = "listOfCoins"
                        ) {
                            composable("listOfCoins") {
                                ShowCoins(
                                    navController,
                                    listOfCoins
                                )
                            }
                            composable("coinInfo") {
                                ShowCoinInfoComposable(navController)
                            }
                        }

                    }
                }
            }
        }
    }

    @Composable
    private fun ShowCoinInfoComposable(navController: NavHostController) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = getString(R.string.app_name))
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(Icons.Filled.ArrowBack, "")
                        }
                    },
                    backgroundColor = Color.Blue,
                    contentColor = Color.White,
                    elevation = 12.dp
                )

            },
            modifier = Modifier.padding(0.dp), content = {
            Column(modifier = Modifier.padding(0.dp)) {
                Row {
                    rootCoins?.chartName?.let { it1 ->
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = it1
                        )
                    }
                }
                Row {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = (rootCoins?.bpi?.eur?.code)
                            + " : "
                            + rootCoins?.bpi?.eur?.rate
                    )
                }
                Row {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = rootCoins?.bpi?.gbp?.code
                            + " : "
                            + rootCoins?.bpi?.gbp?.rate
                    )
                }
                Row {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = rootCoins?.bpi?.usd?.code
                            + " : "
                            + rootCoins?.bpi?.usd?.rate
                    )
                }
            }
        })
    }

    @Composable
    fun ShowCoins(navController: NavHostController, coins: List<Coins>) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = getString(R.string.app_name))
                    },
                    backgroundColor = Color.Blue,
                    contentColor = Color.White,
                    elevation = 12.dp
                )

            },
            modifier = Modifier.padding(0.dp),
            content = {
            TableComposable(navController, coins)
        })
    }

    @Composable
    fun TableComposable(navController: NavHostController, coins: List<Coins>) {
        Scaffold(
            modifier = Modifier.padding(0.dp),
            content = {
                LazyColumn(
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, 0.dp)
                ) {
                    items(items = coins, itemContent = { coin ->
                        Column(modifier = Modifier.clickable(
                            onClick = {
                                rootCoins = coin
                                navController.navigate("coinInfo")
                            }
                        )) {
                            Row {
                                Text(
                                    modifier = Modifier.padding(5.dp),
                                    text = coin.chartName
                                )
                            }
                            Row {
                                Text(
                                    modifier = Modifier.padding(5.dp),
                                    text = coin.bpi.eur.code
                                        + " : "
                                        + coin.bpi.eur.rate
                                )
                            }
                            Row {
                                Text(
                                    modifier = Modifier.padding(5.dp),
                                    text = coin.bpi.gbp.code
                                        + " : "
                                        + coin.bpi.gbp.rate
                                )
                            }
                            Row {
                                Text(
                                    modifier = Modifier.padding(5.dp),
                                    text = coin.bpi.usd.code
                                        + " : "
                                        + coin.bpi.usd.rate
                                )
                            }
                            Row {
                                Divider(
                                    color = Color.Black,
                                    thickness = 1.dp
                                )
                            }
                        }
                    })
                }
            })
    }
}