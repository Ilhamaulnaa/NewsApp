package com.ilham.newsapp.nvgraph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.ilham.newsapp.presentation.home.HomeScreen
import com.ilham.newsapp.presentation.home.HomeViewModel
import com.ilham.newsapp.presentation.onboarding.OnBoardingScreen
import com.ilham.newsapp.presentation.onboarding.OnBoardingViewModel
import com.ilham.newsapp.ui.common.ArticleCardShimmerEffect

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun NavGraph(
    startDestination: String
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination ){
        navigation(
            route = Route.AppStartingNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ){
            composable(
                route = Route.OnBoardingScreen.route
            ){
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ){
            composable(
                route = Route.NewsNavigatorScreen.route
            ){

                val viewModel: HomeViewModel = hiltViewModel()
                val articlesItem = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(articlesItem = articlesItem, navigate = {})
                ArticleCardShimmerEffect()
            }
        }
    }

}