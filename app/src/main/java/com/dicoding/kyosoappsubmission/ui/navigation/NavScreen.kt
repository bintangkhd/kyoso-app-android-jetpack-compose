package com.dicoding.kyosoappsubmission.ui.navigation

sealed class NavScreen(val route: String) {
    object MainPage : NavScreen("home")
    object AnimeDetailPage : NavScreen("home/{tourismId}") {
        fun createRoute(tourismId: Int) = "home/$tourismId"
    }
    object AboutPage : NavScreen("about_page")
    object FavoritePage : NavScreen("favorite")
}