package com.ilham.newsapp.presentation.search

import androidx.paging.PagingData
import com.ilham.data.remote.dto.ArticlesItem
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articlesItem: Flow<PagingData<ArticlesItem>>? = null
)
