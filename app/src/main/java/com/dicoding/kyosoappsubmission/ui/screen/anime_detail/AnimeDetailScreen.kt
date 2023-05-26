package com.dicoding.kyosoappsubmission.ui.screen.anime_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.dicoding.kyosoappsubmission.data.local_data.Anime
import com.dicoding.kyosoappsubmission.data.repo.ResultCondition
import com.dicoding.kyosoappsubmission.ui.components.ErrorOccurred
import com.dicoding.kyosoappsubmission.R
import com.dicoding.kyosoappsubmission.utils.numberFormat

@Composable
fun AnimeDetailScreen(id: Int, controller: NavController) {
    val viewModel = hiltViewModel<AnimeDetailViewModel>()
    viewModel.animeDetail.collectAsState(ResultCondition.LoadingState).value.let { result ->
        when (result) {
            is ResultCondition.LoadingState -> viewModel.getDetailAnime(id)
            is ResultCondition.ErrorState -> ErrorOccurred()
            is ResultCondition.SuccessState -> {
                AnimeDetail(
                    result.data,
                    controller,
                    viewModel::updatesAnimeFavorite
                )
            }
        }
    }
}

@Composable
fun AnimeDetail(
    anime: Anime,
    controller: NavController,
    updateFavoriteAnime: (id: Int, isFavorite: Boolean) -> Unit,
) {
    val (id, name, photoUrl, author, releaseDate, ranked, rating, reviewers, synopsis, favorite) = anime

    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp)
        ) {
            Box {
                AsyncImage(
                    model = photoUrl,
                    contentDescription = name,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.template_image),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,

                            ) {
                            Text(
                                text = stringResource(R.string.author),
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                            )
                            Spacer(modifier = Modifier.width(24.dp))
                            Text(
                                text = stringResource(R.string.colon),
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                            )
                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = author,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.first_aired),
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = stringResource(R.string.colon),
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                            )
                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = releaseDate,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                            )
                        }


                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = if (favorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                            tint = if (favorite) Color.Red else MaterialTheme.colors.onSurface,
                            contentDescription = name,
                            modifier = Modifier
                                .size(30.dp)
                                .clip(RoundedCornerShape(100))
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) {
                                    updateFavoriteAnime(id ?: 0, !favorite)
                                },
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = stringResource(R.string.add_to_favorite),
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                        )

                    }

                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = ranked,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = stringResource(R.string.popularity),
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                        )

                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                imageVector = Icons.Rounded.Star,
                                contentDescription = name,
                                tint = Color(0xFFFFCC00),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = rating.toString(),
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                        }
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = "(${numberFormat(reviewers)})",
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                        )

                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.synopsis),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = synopsis,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                )

            }
        }

        IconButton(
            onClick = { controller.navigateUp() },
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp)
                .align(Alignment.TopStart)
                .clip(CircleShape)
                .size(40.dp)
                .testTag("back_button")
                .background(Color(0xFF87CEEB))
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                tint = Color.White,
                contentDescription = "Back",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeDetailPreview() {
    val anime = Anime(
        id = 1,
        name = "Jujutsu Kaisen",
        photoUrl = "https://example.com/image.jpg",
        author = "Gege Akutami",
        releaseDate = "Jun 14, 2014",
        ranked = "Rank #1",
        rating = 8.59,
        reviewers = 1349876,
        synopsis = "Aware of the terrifying godlike power that has fallen into his hands, Light—under the alias Kira—follows his wicked sense of justice with the ultimate goal of cleansing the world of all evil-doers. The meticulous mastermind detective L is already on his trail, but as Light's brilliance rivals L's, the grand chase for Kira turns into an intense battle of wits that can only end when one of them is dead.",
        favorite = true
    )

    AnimeDetail(
        anime = anime,
        controller = rememberNavController(),
        updateFavoriteAnime = { id, isFavorite -> }
    )

}