package com.mmisoft.animalsretrofit.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mmisoft.animalsretrofit.MainViewModel
import com.mmisoft.animalsretrofit.ui.components.AnimalListItem
import com.mmisoft.animalsretrofit.ui.components.NetworkAlertDialog
import com.mmisoft.animalsretrofit.ui.destinations.DetailScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    // initialising the ViewModel
    val viewModel: MainViewModel = hiltViewModel()

    // collecting the state of the ViewModel
    val animals by viewModel.animalsState.collectAsState()
    val openDialog by viewModel.openDialog.collectAsState()

    var refreshing by remember { mutableStateOf(false) }
    LaunchedEffect(refreshing) {
        if (refreshing) {
            // we need the viewModelScope as the networking cannot be done on the Main thread
            viewModel.checkInternetConnection()
            delay(500)
            if (!openDialog) viewModel.refreshAnimals()
            refreshing = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Animals")
                }
            )
        }
    ) { scaffoldPadding ->
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = refreshing),
            onRefresh = { refreshing = true },
            modifier = Modifier.padding(scaffoldPadding)
        ) {
            if (animals.isEmpty()) {
                // using item{} to have a composableScope to call the CircularProgressIndicator
                if (!openDialog) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(150.dp),
                            color = Color.Gray
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(scaffoldPadding),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    items(animals) { animal ->
                        Divider(color = Color.Gray, thickness = 1.dp)
                        AnimalListItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp),
                            animal = animal,
                            onCLick = {
                                // navigates to the details screen
                                navigator.navigate(
                                    DetailScreenDestination(
                                        id = 0,
                                        animal = animal
                                    )
                                )
                            }
                        )
                    }
                }
            }
            Column(modifier = Modifier.fillMaxSize()) {
                if (openDialog) {
                    NetworkAlertDialog(
                        dismissDialog = {
                            viewModel.checkInternetConnection()
                        })
                }
            }
        }
    }
}