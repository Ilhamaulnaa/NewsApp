package com.ilham.newsapp.presentation.bookmark

import androidx.paging.PagingData
import com.ilham.data.remote.dto.ArticlesItem
import kotlinx.coroutines.flow.Flow

data class BookmarkState(
    val articlesItem: List<ArticlesItem> = emptyList()
)
