package com.ilham.newsapp.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.ilham.data.remote.dto.ArticlesItem
import com.ilham.newsapp.presentation.Dimens
import com.ilham.newsapp.presentation.common.ArticleCard
import com.ilham.newsapp.ui.animtation.LoadingAnimation

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articlesItem: List<ArticlesItem>,
    onClick: (ArticlesItem) -> Unit
) {

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1),
        contentPadding = PaddingValues(Dimens.ExtraSmallPadding2)
    ){
        items(count = articlesItem.size){
            val article = articlesItem[it]
            ArticleCard(articlesItem = article, onClick = { onClick(article) })
        }
    }
}
@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articlesItem: LazyPagingItems<ArticlesItem>,
    onClick: (ArticlesItem) -> Unit
) {

    val handlePagingResult = handlePagingResult(articlesItem = articlesItem)
    if (handlePagingResult){
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1),
            contentPadding = PaddingValues(Dimens.ExtraSmallPadding2)
        ){
            items(count = articlesItem.itemCount){
                articlesItem[it]?.let {
                    ArticleCard(articlesItem = it, onClick = { onClick(it) })
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    articlesItem: LazyPagingItems<ArticlesItem>
): Boolean {

    val loadState = articlesItem.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when{
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
//            LoadingAnimation()
            false
        }
        error != null -> {
            EmptyScreen()
            false
        }
        else -> {
            true
        }
    }

}

@Composable
fun ShimmerEffect() {

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1)
    ) {
        repeat(10){
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = Dimens.MediumPadding1)
            )
        }
    }

}