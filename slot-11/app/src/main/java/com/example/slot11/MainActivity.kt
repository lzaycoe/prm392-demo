package com.example.slot11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.slot11.data.repository.AuthRepository
import com.example.slot11.ui.screens.HomeScreen
import com.example.slot11.ui.screens.LoginScreen
import com.example.slot11.ui.screens.SignupScreen
import com.example.slot11.ui.theme.Slot11Theme
import com.example.slot11.viewmodel.AuthViewModel
import com.example.slot11.viewmodel.AuthViewModelFactory
import supabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Slot11Theme {
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    val authRepository = AuthRepository(supabase)
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(authRepository))

    LaunchedEffect(viewModel.isLoggedIn.value) {
        if (viewModel.isLoggedIn.value) {
            navController.navigate("home") {
                popUpTo("login") {
                    inclusive = true
                }
            }
        } else {
            navController.navigate("login") {
                popUpTo("home") {
                    inclusive = true
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                viewModel = viewModel,
                onNavigateToSignUp = {
                    navController.navigate("signup")
                }
            )
        }

        composable("signup") {
            SignupScreen(
                viewModel = viewModel,
                onNavigateToLogin = {
                    navController.navigate("login")
                }
            )
        }

        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}



