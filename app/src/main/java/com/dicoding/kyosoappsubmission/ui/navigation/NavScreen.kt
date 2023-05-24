package com.dicoding.kyosoappsubmission.ui.navigation

sealed class NavScreen(val route: String) {
    object HomePage : NavScreen("home")
    object FavoritePage : NavScreen("favorite")
    object ProfilePage : NavScreen("profile")
    object AnimeDetailPage : NavScreen("home/{tourismId}") {
        fun createRoute(tourismId: Int) = "home/$tourismId"
    }
}