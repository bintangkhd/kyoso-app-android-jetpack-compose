package com.dicoding.kyosoappsubmission.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dicoding.kyosoappsubmission.data.local_data.Anime
import com.dicoding.kyosoappsubmission.data.repo.ResultCondition
import com.dicoding.kyosoappsubmission.ui.components.*

@Composable
fun HomeScreen(controller: NavController) {
    val viewModel = hiltViewModel<MainViewModel>()
    val homeState by viewModel.homeState

    viewModel.animeList.collectAsState(ResultCondition.LoadingState).value.let { resultState ->
        when (resultState) {
            is ResultCondition.LoadingState -> LoadingScreen()
            is ResultCondition.ErrorState -> ErrorOccurred()
            is ResultCondition.SuccessState -> {
                AnimeList(
                    animeList = resultState.data,
                    controller = controller,
                    query = homeState.query,
                    onQueryChange = viewModel::onQueryChange,
                )
            }
        }
    }
}

@Composable
fun AnimeList(
    animeList: List<Anime>,
    controller: NavController,
    query: String,
    onQueryChange: (String) -> Unit,
) {
    Column {
        CustomAppBar(query = query, onQueryChange = onQueryChange)
        when (animeList.isEmpty()) {
            true -> DataNotFound()
            false -> LoadLazyAnimeList(animeList, controller)
        }
    }
}


