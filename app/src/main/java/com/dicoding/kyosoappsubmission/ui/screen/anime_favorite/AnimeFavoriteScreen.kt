package com.dicoding.kyosoappsubmission.ui.screen.anime_favorite

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dicoding.kyosoappsubmission.data.local_data.Anime
import com.dicoding.kyosoappsubmission.data.repo.ResultCondition
import com.dicoding.kyosoappsubmission.ui.components.DataNotFound
import com.dicoding.kyosoappsubmission.ui.components.ErrorOccurred
import com.dicoding.kyosoappsubmission.ui.components.LoadLazyAnimeList
import com.dicoding.kyosoappsubmission.ui.components.LoadingScreen
import com.dicoding.kyosoappsubmission.R

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

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = { controller.navigateUp() },
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp)
                    .size(40.dp)
                    .testTag("back_button")
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = Color(0xFF87CEEB),
                    contentDescription = "Back",
                )
            }
            Text(
                text = stringResource(R.string.favorite),
                color = Color(0xFF87CEEB),
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp)
            )
        }
        when (animeFavoriteList.isEmpty()) {
            true -> DataNotFound()
            false -> LoadLazyAnimeList(animeFavoriteList, controller)
        }
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






