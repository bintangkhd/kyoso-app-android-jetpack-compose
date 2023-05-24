package com.dicoding.kyosoappsubmission.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.dicoding.kyosoappsubmission.R
import com.dicoding.kyosoappsubmission.data.local_data.Anime
import com.dicoding.kyosoappsubmission.ui.navigation.NavScreen


@Composable
fun AnimeCardItem(
    anime: Anime,
    controller: NavController,
) {
    val (id, name, photoUrl, author, _, ranked, rating, _, _, _) = anime

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, Color.LightGray.copy(0.5f), RoundedCornerShape(20.dp))
            .clickable { controller.navigate(NavScreen.AnimeDetailPage.createRoute(id ?: 0)) },
    ) {
        Column {
            Box {
                AsyncImage(
                    model = photoUrl,
                    contentDescription = name,
                    contentScale = ContentScale.FillWidth,
                    placeholder = painterResource(R.drawable.template_image),
                    modifier = Modifier.fillMaxWidth()
                )
                Box(
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.5f))
                        .align(Alignment.BottomEnd)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = ranked,
                            style = MaterialTheme.typography.body2.copy(fontSize = 12.sp),
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.h6.copy(fontSize = 24.sp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Star,
                            contentDescription = name,
                            tint = Color(0xFFFFCC00),
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = rating.toString(),
                            style = MaterialTheme.typography.h6
                        )
                    }

                }
                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "By",
                        style = MaterialTheme.typography.body2
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = author,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}

@Composable
fun LoadLazyAnimeList(
    animeList: List<Anime>,
    controller: NavController,
) {
    LazyColumn {
        items(animeList, key = { it.name }) { anime ->
            AnimeCardItem(anime, controller)
        }
    }
}

@Preview
@Composable
fun AnimeCardItemPreview() {
    val dummyAnime = Anime(
        id = 1,
        name = "Jujutsu Kaisen",
        photoUrl = "https://wallpapercave.com/wp/wp5165281.jpg",
        author = "Gege Akutami",
        releaseDate = "2022-01-01",
        ranked = "Popularity Rank #1",
        rating = 8.78,
        reviewers = 10007849,
        favorite = false,
        synopsis = "As a wild youth, elementary school student Shouya Ishida sought to beat boredom in the cruelest ways. When the deaf Shouko Nishimiya transfers into his class, Shouya and the rest of his class thoughtlessly bully her for fun."
    )

    val navController = rememberNavController()

    AnimeCardItem(
        anime = dummyAnime,
        controller = navController,
    )
}