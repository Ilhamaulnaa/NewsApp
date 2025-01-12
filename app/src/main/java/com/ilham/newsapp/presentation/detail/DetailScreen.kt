package com.ilham.newsapp.presentation.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ilham.data.remote.dto.ArticlesItem
import com.ilham.data.remote.dto.Source
import com.ilham.newsapp.R
import com.ilham.newsapp.presentation.Dimens
import com.ilham.newsapp.presentation.Dimens.ArticleImageHeight
import com.ilham.newsapp.presentation.Dimens.ExtraSmallPadding
import com.ilham.newsapp.presentation.Dimens.ExtraSmallPadding2
import com.ilham.newsapp.presentation.Dimens.MediumPadding1
import com.ilham.newsapp.presentation.detail.component.TopBarDetail
import com.ilham.newsapp.ui.theme.NewsAppTheme

@ExperimentalMaterial3Api
@Composable
fun DetailScreen(
    articlesItem: ArticlesItem,
    event: (DetailEvent) -> Unit,
    navigateUp: () -> Unit
) {

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBarDetail(
                onBackClick = navigateUp,
                onBookmarkClick = {
                  event(DetailEvent.SaveArticleItem)
                },
                onShareClick = {
                    Intent(Intent.ACTION_SEND).also {
                        it.putExtra(Intent.EXTRA_TEXT, articlesItem.url)
                        it.type = "text/plain"
                        if (it.resolveActivity(context.packageManager) != null){
                            context.startActivity(it)
                        }
                    }
                },
                onBrowsingClick = {
                  Intent(Intent.ACTION_VIEW).also {
                      it.data = Uri.parse(articlesItem.url)
                      if (it.resolveActivity(context.packageManager) != null){
                          context.startActivity(it)
                      }
                  }
                },
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                end = MediumPadding1,
                top = MediumPadding1
            )
        ){
            item {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    model = ImageRequest.Builder(context).data(articlesItem.urlToImage).build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(ExtraSmallPadding2))
                Text(
                    text = articlesItem.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(id = R.color.text_title)
                )
                Spacer(modifier = Modifier.height(ExtraSmallPadding))
                Text(
                    text = articlesItem.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.body)
                )
            }
        }

    }


}

@ExperimentalMaterial3Api
@Preview
@Composable
fun SectionDetailPreview() {
    NewsAppTheme {
        Surface {
            val articlesItem = ArticlesItem(
                title = "The Crypto Industry Is Helping Trump Pick SEC Chair",
                description = "The president-elect's transition team is consulting with industry leaders as it vets potential replacements for outgoing chair Gary Gensler, sources tell WIRED." ,
                content = "In July, at a bitcoin conference in Nashville, Tennessee, Trump pledged to fire Gensler if reelected, drawing perhaps the most raucous applause of the night. I will appoint an SEC chair who will buil… [+2635 chars]",
                publishedAt = "2024-12-03T15:41:59Z",
                source = Source(
                    id = "", name = "Gizmodo.com",
                ),
                url = "https://www.wired.com/story/crypto-candidates-front-runners-sec-race/",
                urlToImage = "https://media.wired.com/photos/6745db10e149b18ca8e2b8d8/191:100/w_1280,c_limit/GettyImages-93181618.jpg"
            )
            DetailScreen(
                articlesItem = articlesItem,
                event = {},
                navigateUp = {}
            )
        }
    }
}