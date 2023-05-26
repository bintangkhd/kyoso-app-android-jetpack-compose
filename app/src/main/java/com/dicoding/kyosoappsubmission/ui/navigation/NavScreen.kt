package com.dicoding.kyosoappsubmission.ui.navigation

sealed class NavScreen(val route: String) {
    object MainPage : NavScreen("home")
    object AnimeDetailPage : NavScreen("home/{animeId}") {
        fun createRoute(animeId: Int) = "home/$animeId"
    }
    object AboutPage : NavScreen("about_page")
    object AnimeFavoritePage : NavScreen("favorite")
}

