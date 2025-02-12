package com.gabriel.authapp

import AuthViewModelFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gabriel.authapp.data.AuthRepository
import com.gabriel.authapp.ui.screen.ForgotPasswordScreen
import com.gabriel.authapp.ui.screen.HomeScreen
import com.gabriel.authapp.ui.screen.LoginScreen
import com.gabriel.authapp.ui.screen.RegisterScreen
import com.gabriel.authapp.ui.theme.AuthAppTheme
import com.gabriel.authapp.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = AuthRepository()
        val authViewModel =
            ViewModelProvider(this, AuthViewModelFactory(repository)).get(AuthViewModel::class.java)

        setContent {
            val navController: NavHostController = rememberNavController()

            NavHost(navController = navController, startDestination = "login") {
                composable("login") { LoginScreen(authViewModel, navController) }
                composable("register") { RegisterScreen(authViewModel, navController) }
                composable("forgotPassword") { ForgotPasswordScreen(authViewModel, navController) }
                composable("home") { HomeScreen(authViewModel, navController) }
            }
        }
    }
}
