package com.ilham.newsapp.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ilham.data.remote.dto.ArticlesItem
import com.ilham.data.remote.dto.Source
import com.ilham.newsapp.R
import com.ilham.newsapp.presentation.Dimens.ArticleCardSize
import com.ilham.newsapp.presentation.Dimens.ExtraSmallPadding2
import com.ilham.newsapp.presentation.Dimens.SmallIconSize
import com.ilham.newsapp.ui.theme.BlueGray
import com.ilham.newsapp.ui.theme.NewsAppTheme
import kotlin.coroutines.CoroutineContext

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    articlesItem: ArticlesItem,
    onClick: () -> Unit
) {

    val context = LocalContext.current

    Row (
        modifier = modifier.clickable { onClick }
    ){

        AsyncImage(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(articlesItem.urlToImage).build(),
            contentDescription = null
        )

        Column (
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceAround
        ){
            Text(
                text = articlesItem.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.text_title),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = articlesItem.source?.name.toString(),
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Icon(
                    modifier = Modifier.size(SmallIconSize),
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    tint = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Text(
                    text = articlesItem.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )
            }
        }

    }

}

@Preview
@Composable
fun ArticleCardPreview() {
    NewsAppTheme {
        Surface {
            val articlesItem = ArticlesItem(
                urlToImage = "",
                description = "ship worker dies on board vessel",
                author = "Ilham",
                publishedAt = "2 hours",
                title = "What is it like being on standby at a world cup?",
                source = Source(
                    name = "BBC"
                )
            )
            ArticleCard(
                modifier = Modifier.padding(16.dp),
                articlesItem = articlesItem,
                onClick = {}
            )
        }
    }
}