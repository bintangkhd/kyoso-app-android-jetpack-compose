package com.dicoding.kyosoappsubmission.ui.screen.anime_favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dicoding.kyosoappsubmission.data.local_data.Anime
import com.dicoding.kyosoappsubmission.data.repo.ResultCondition
import com.dicoding.kyosoappsubmission.ui.components.DataNotFound
import com.dicoding.kyosoappsubmission.ui.components.ErrorOccurred
import com.dicoding.kyosoappsubmission.ui.components.LoadLazyAnimeList
import com.dicoding.kyosoappsubmission.ui.components.LoadingScreen

@Composable
fun AnimeFavoriteScreen(controller: NavController) {
    val viewModel = hiltViewModel<AnimeFavoriteViewModel>()

    viewModel.animeFavoriteList.collectAsState(ResultCondition.LoadingState).value.let { result ->
        when (result) {
            is ResultCondition.LoadingState -> LoadingScreen()
            is ResultCondition.ErrorState -> ErrorOccurred()
            is ResultCondition.SuccessState -> {
                AnimeFavoriteList(
                    animeFavoriteList = result.data,
                    controller = controller,
                )
            }
        }
    }
}

@Composable
fun AnimeFavoriteList(
    animeFavoriteList: List<Anime>,
    controller: NavController,
) {
    when (animeFavoriteList.isEmpty()) {
        true -> DataNotFound()
        false -> LoadLazyAnimeList(animeFavoriteList, controller)
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeFavoriteListPreview() {
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

    AnimeFavoriteList(animeFavoriteList = animeList, controller = navController)

}






