package com.ilham.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ilham.newsapp.nvgraph.NavGraph
import com.ilham.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//    @Inject
//    lateinit var useCases: AppEntryUseCases

    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //to changed content view
        WindowCompat.setDecorFitsSystemWindows(window, true)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.splashCondition
            }
        }
//        lifecycleScope.launch {
//            useCases.readAppEntry().collect{
//                Log.d("test", it.toString())
//            }
//        }
        setContent {
            NewsAppTheme {

                val isSystemDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()

                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemDarkMode
                    )
                }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val viewModel: OnBoardingViewModel = hiltViewModel()
//                    OnBoardingScreen(
//                        event = viewModel::onEvent
//                    )

                    val startDestination = viewModel.startDestination
                    NavGraph(startDestination = startDestination)

                }
            }
        }
    }
}

//80f2c865a8674311b77df1470eb77b77