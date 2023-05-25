package com.dicoding.kyosoappsubmission.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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

@Preview(showBackground = true)
@Composable
fun AnimeListPreview() {
    val fakeData1 = Anime(
        id = 1,
        name="Attack on Titan",
        photoUrl = "https://wallpapercave.com/wp/wp2114772.jpg",
        author = "Hajime Isayama",
        releaseDate = "Apr 7, 2013",
        ranked = "Rank #1",
        rating = 8.54,
        reviewers = 2649483,
        synopsis = "Example 1"
    )

    val fakeData2 = Anime(
        id = 2,
        name="Death Note",
        photoUrl = "https://wallpapercave.com/wp/er4khNt.jpg",
        author = "Tsugumi Ohba",
        releaseDate = "Oct 4, 2006",
        ranked = "Rank #2",
        rating = 8.62,
        reviewers = 2608368,
        synopsis = "Example 2"
    )

    val navController = rememberNavController()
    val animeList = listOf(
        fakeData1,
        fakeData2
    )
    var query by remember { mutableStateOf("") }

    AnimeList(
        animeList = animeList,
        controller = navController,
        query = query,
        onQueryChange = { newQuery -> query = newQuery }
    )

}


