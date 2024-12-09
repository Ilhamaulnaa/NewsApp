package com.ilham.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ilham.data.remote.pagingsource.NewsPagingSource
import com.ilham.data.remote.dto.ArticlesItem
import com.ilham.data.remote.pagingsource.SearchPagingSource
import com.ilham.data.remote.service.NewsApi
import com.ilham.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi
): NewsRepository {

    override fun getNews(sources: List<String>): Flow<PagingData<ArticlesItem>> {

        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    source = sources.joinToString(separator = ",")
                )
            }
        ).flow

    }

    override fun search(
        searchQuery: String,
        sources: List<String>
    ): Flow<PagingData<ArticlesItem>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchPagingSource(
                    newsApi = newsApi,
                    searchQuery = searchQuery,
                    source = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

}