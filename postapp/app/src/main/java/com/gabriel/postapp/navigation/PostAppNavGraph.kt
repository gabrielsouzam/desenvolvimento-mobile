package com.gabriel.postapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gabriel.postapp.ui.screens.PostScreen
import com.gabriel.postapp.ui.screens.UserScreen

sealed class PostAppScreen(val route: String) {
    object User : PostAppScreen("user_screen")
    object Post : PostAppScreen("post_screen")
}

@Composable
fun PostAppNavGraph(
    modifier: Modifier = Modifier,
    startDestination: String = PostAppScreen.User.route
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Rota para UserScreen
        composable(PostAppScreen.User.route) {
            UserScreen(modifier = modifier)
        }
        // Rota para PostScreen
        composable(PostAppScreen.Post.route) {
            PostScreen(modifier = modifier)
        }
    }
}
