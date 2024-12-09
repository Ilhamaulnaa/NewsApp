package com.ilham.newsapp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.ilham.data.remote.dto.ArticlesItem
import com.ilham.newsapp.R
import com.ilham.newsapp.nvgraph.Route
import com.ilham.newsapp.presentation.Dimens
import com.ilham.newsapp.presentation.Dimens.MediumPadding1
import com.ilham.newsapp.ui.common.ArticlesList
import com.ilham.newsapp.ui.searchbar.SearchBar

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    articlesItem: LazyPagingItems<ArticlesItem>,
    navigate: (String) -> Unit
) {

    val titles by remember {
        derivedStateOf {
            if (articlesItem.itemCount > 10){
                articlesItem.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = "\uD83d\uDFE5") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimens.MediumPadding1)
            .statusBarsPadding(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = Dimens.MediumPadding1)
        )
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        SearchBar(
            modifier = Modifier.padding(horizontal = Dimens.ExtraSmallPadding2),
            value = "",
            onValueChange = {},
            readOnly = true,
            onClick = {
                      navigate(Route.SearchScreen.route)
            },
            onSearch = {}
        )
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimens.MediumPadding1)
                .basicMarquee(),
            color = colorResource(id = R.color.placeholder),
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        ArticlesList(
            articlesItem = articlesItem,
            onClick = {
                navigate(Route.DetailScreen.route)
            }
        )
    }

}