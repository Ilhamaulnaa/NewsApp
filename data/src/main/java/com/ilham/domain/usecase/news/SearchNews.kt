package com.ilham.domain.usecase.news

import androidx.paging.PagingData
import com.ilham.data.remote.dto.ArticlesItem
import com.ilham.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke (searchQuery:String, sources: List<String>): Flow<PagingData<ArticlesItem>> {
        return newsRepository.search(searchQuery = searchQuery, sources = sources)
    }


}