package com.dicoding.kyosoappsubmission.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val title: String,
    val icon: ImageVector,
    val screen: NavScreen,
    val contentDescription: String,
)