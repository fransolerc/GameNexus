package ui.components.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import entity.enums.BottomGameBarDestination
import interfaces.GameInterface
import viewmodels.GameListViewModel
import viewmodels.PlatformInfoViewModel

@Composable
fun GameList(
    gameInterface: GameInterface,
    platformId: Int, currentPage: Int
) {

    val gameListViewModel = GameListViewModel(platformId,currentPage)
    val platformViewModel = viewModel<PlatformInfoViewModel>()

    LaunchedEffect(Unit) {
        platformViewModel.onInit(platformId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomGameNavigationBar(
                items = BottomGameBarDestination.entries,
                gameInterface = gameInterface,
                gameListViewModel = gameListViewModel
            )

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(gameListViewModel.gameList) { game ->
                    GameItem(game = game) {
                        gameInterface.onClickGame(game)
                    }
                }
            }
        }
    }
}
