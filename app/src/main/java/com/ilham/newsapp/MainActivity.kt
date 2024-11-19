package com.ilham.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.ilham.newsapp.presentation.onboarding.OnBoardingScreen
import com.ilham.newsapp.ui.theme.NewsAppTheme

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //to changed content view
        WindowCompat.setDecorFitsSystemWindows(window, true)
        installSplashScreen()
        setContent {
            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OnBoardingScreen()
                }
            }
        }
    }
}