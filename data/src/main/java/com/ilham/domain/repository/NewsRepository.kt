package com.ilham.domain.repository

import androidx.paging.PagingData
import com.ilham.data.remote.dto.ArticlesItem
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<ArticlesItem>>

}