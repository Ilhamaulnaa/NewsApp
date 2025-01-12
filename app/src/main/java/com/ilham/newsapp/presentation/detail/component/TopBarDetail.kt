package com.ilham.newsapp.presentation.detail.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.ilham.newsapp.R
import com.ilham.newsapp.presentation.Dimens.ExtraSmallPadding2
import com.ilham.newsapp.presentation.Dimens.IconSize
import com.ilham.newsapp.ui.theme.NewsAppTheme

@ExperimentalMaterial3Api
@Composable
fun TopBarDetail(
    onBookmarkClick: () -> Unit,
    onShareClick: () -> Unit,
    onBrowsingClick: () -> Unit,
    onBackClick: () -> Unit
) {

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body)
        ),
        navigationIcon = {
             IconButton(onClick = onBackClick) {
                 Icon(
                     painter = painterResource(id = R.drawable.ic_back_arrow),
                     contentDescription = null
                 )
             }
        },
        actions = {
            IconButton(onClick = onBookmarkClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bookmark),
                    contentDescription = null
                )
            }
            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = null
                )
            }
            IconButton(onClick = onBrowsingClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_network),
                    contentDescription = null
                )
            }
        }
    )

}

@ExperimentalMaterial3Api
@Preview
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TopBarDetailPreview() {
    NewsAppTheme {
        Surface {
            TopBarDetail(
                onBookmarkClick = {},
                onBackClick = {},
                onBrowsingClick = {},
                onShareClick = {}
            )
        }
    }
}