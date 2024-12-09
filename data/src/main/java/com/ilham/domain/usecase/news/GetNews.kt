package com.ilham.domain.usecase.news

import androidx.paging.PagingData
import com.ilham.data.remote.dto.ArticlesItem
import com.ilham.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke (sources: List<String>): Flow<PagingData<ArticlesItem>>{
        return newsRepository.getNews(sources = sources)
    }

}