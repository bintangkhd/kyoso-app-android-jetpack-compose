package com.dicoding.kyosoappsubmission.ui.main_app

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.kyosoappsubmission.ui.navigation.NavItem
import com.dicoding.kyosoappsubmission.ui.navigation.NavScreen
import com.dicoding.kyosoappsubmission.ui.screen.about.AboutScreen
import com.dicoding.kyosoappsubmission.ui.screen.anime_detail.AnimeDetailScreen
import com.dicoding.kyosoappsubmission.ui.screen.anime_favorite.AnimeFavoriteScreen
import com.dicoding.kyosoappsubmission.ui.screen.main.MainScreen
import com.dicoding.kyosoappsubmission.R

@Composable
fun KyosoMain(modifier: Modifier = Modifier) {
    val controller = rememberNavController()

    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val scaffoldState = rememberScaffoldState()
    val shouldShowNavbar = currentRoute != null && currentRoute != NavScreen.AnimeDetailPage.route && currentRoute != NavScreen.AnimeFavoritePage.route

    Scaffold(
        bottomBar = {
            if (shouldShowNavbar) {
                Navbar(controller, currentRoute)
            }
        },
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(hostState = it) { data ->
                Snackbar(snackbarData = data, shape = RoundedCornerShape(8.dp))
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = controller,
            startDestination = NavScreen.MainPage.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavScreen.MainPage.route) {
                MainScreen(
                    controller = controller,
                    moveToFavoriteScreen = {
                        controller.navigate(NavScreen.AnimeFavoritePage.route)
                    }
                )
            }
            composable(
                route = NavScreen.AnimeDetailPage.route,
                arguments = listOf(
                    navArgument("animeId") { type = NavType.IntType }
                )
            ) {
                val animeId = it.arguments?.getInt("animeId") ?: 0
                AnimeDetailScreen(animeId, controller)
            }
            composable(NavScreen.AboutPage.route) {
                AboutScreen()
            }
            composable(NavScreen.AnimeFavoritePage.route) {
                AnimeFavoriteScreen(controller)
            }
        }
    }
}

@Composable
fun Navbar(
    navController: NavHostController,
    currentRoute: String?,
) {
    val navigationItems = listOf(
        NavItem(
            title = stringResource(R.string.home),
            icon = Icons.Rounded.Home,
            screen = NavScreen.MainPage,
            contentDescription = "home_page"
        ),
        NavItem(
            title = stringResource(R.string.about),
            icon = Icons.Rounded.Person,
            screen = NavScreen.AboutPage,
            contentDescription = "about_page"
        ),
    )

    BottomNavigation(backgroundColor = Color.White) {
        navigationItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                selectedContentColor = Color(0xFF87CEEB),
                unselectedContentColor = Color.Gray,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}
