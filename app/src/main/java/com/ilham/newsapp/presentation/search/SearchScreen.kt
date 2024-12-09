package com.ilham.newsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.ilham.newsapp.nvgraph.Route
import com.ilham.newsapp.presentation.Dimens.MediumPadding1
import com.ilham.newsapp.ui.common.ArticlesList
import com.ilham.newsapp.ui.searchbar.SearchBar

@ExperimentalMaterial3Api
@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigate: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1)
            .statusBarsPadding()
    ) {
        SearchBar(
            value = state.searchQuery,
            onValueChange = {
                            event(SearchEvent.UpdateSearchQuery(searchQuery = it))
            },
            readOnly = false,
            onSearch = {
                event(SearchEvent.SearchNews)
            }
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        state.articlesItem?.let {
            val articlesItem = it.collectAsLazyPagingItems()
            ArticlesList(
                articlesItem = articlesItem,
                onClick = { navigate(Route.DetailScreen.route) }
            )
        }
    }

}