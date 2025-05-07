package com.ilham.newsapp.presentation.news_navigator

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.ilham.data.remote.dto.ArticlesItem
import com.ilham.newsapp.R
import com.ilham.newsapp.nvgraph.Route
import com.ilham.newsapp.presentation.bookmark.BookmarkScreen
import com.ilham.newsapp.presentation.bookmark.BookmarkViewModel
import com.ilham.newsapp.presentation.detail.DetailEvent
import com.ilham.newsapp.presentation.detail.DetailScreen
import com.ilham.newsapp.presentation.detail.DetailViewModel
import com.ilham.newsapp.presentation.home.HomeScreen
import com.ilham.newsapp.presentation.home.HomeViewModel
import com.ilham.newsapp.presentation.news_navigator.component.BottomNavigationItem
import com.ilham.newsapp.presentation.news_navigator.component.NewsBottomNav
import com.ilham.newsapp.presentation.search.SearchScreen
import com.ilham.newsapp.presentation.search.SearchViewModel

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun NewsNavigator() {

    val bottomNavigationItem = listOf(
        BottomNavigationItem(icon = R.drawable.ic_home, text = "home"),
        BottomNavigationItem(icon = R.drawable.ic_search, text = "search"),
        BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "bookmark")
    )

    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value

    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = remember(key1 = backstackState){
        when(backstackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }

    val isBottomVisible = remember(key1 = backstackState) {
        backstackState?.destination?.route == Route.HomeScreen.route ||
                backstackState?.destination?.route == Route.SearchScreen.route ||
                backstackState?.destination?.route == Route.BookmarkScreen.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomVisible) {
                NewsBottomNav(
                    items = bottomNavigationItem,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Route.SearchScreen.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Route.BookmarkScreen.route
                            )
                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ){
            composable(route = Route.HomeScreen.route){
                val viewModel: HomeViewModel = hiltViewModel()
                val article = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articlesItem = article,
                    navigateSearchScreen = {
                       navigateToTab(
                           navController = navController,
                           route = Route.SearchScreen.route
                       )
                    },
                    navigateToDetailsScreen = { article ->
                        navigateToDetails(
                            navController = navController,
                            articlesItem = article
                        )
                    }
                )
            }
            composable(route =  Route.SearchScreen.route){
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetailsScreen = { article ->
                        navigateToDetails(
                            navController = navController,
                            articlesItem = article
                        )
                    }
                )
            }
            composable(route = Route.BookmarkScreen.route){
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(
                    state = state,
                    navigateToDetailsScreen = {
                        navigateToTab(
                            navController = navController,
                            route = Route.DetailScreen.route
                        )
                    }
                )
            }
            composable(route = Route.DetailScreen.route){
                val viewModel: DetailViewModel = hiltViewModel()
                if (viewModel.sideEffect != viewModel.sideEffect){
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT)
                        .show()
                    viewModel.onEvent(DetailEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<ArticlesItem?>("article")
                    ?.let {  article ->
                        DetailScreen(
                            articlesItem = article,
                            event = viewModel::onEvent,
                            navigateUp = {
                                navController.navigateUp()
                            }
                        )
                }
            }
        }
    }

}

private fun navigateToTab(navController: NavController, route: String){
    navController.navigate(route){
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen){
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(navController: NavController, articlesItem: ArticlesItem){
    navController.currentBackStackEntry?.savedStateHandle?.set("article", articlesItem)
    navController.navigate(route = Route.DetailScreen.route)
}
