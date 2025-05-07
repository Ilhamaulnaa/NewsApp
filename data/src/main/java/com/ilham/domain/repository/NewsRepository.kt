package com.ilham.domain.repository

import androidx.paging.PagingData
import com.ilham.data.remote.dto.ArticlesItem
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<ArticlesItem>>

    fun search(searchQuery: String, sources: List<String>): Flow<PagingData<ArticlesItem>>

    suspend fun upsertArticle(article:ArticlesItem)

    fun delete(article:ArticlesItem)

    fun selectArticle():Flow<List<ArticlesItem>>

}