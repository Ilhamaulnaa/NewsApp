package com.ilham.newsapp.nvgraph

sealed class Route(
    val route: String
) {

    object OnBoardingScreen: Route(route = "onBoardingScreen")
    object HomeScreen: Route(route = "homeScreen")
    object DetailScreen: Route(route = "detailScreen")
    object AboutScreen: Route(route = "aboutScreen")
    object SearchScreen: Route(route = "searchScreen")
    object BookmarkScreen: Route(route = "bookmarkScreen")
    object AppStartingNavigation: Route(route = "appStartingNavigation")
    object NewsNavigation: Route(route = "newsNavigation")
    object NewsNavigatorScreen: Route(route = "newsNavigatorScreen")


}
