package com.ilham.data.remote.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ilham.data.remote.dto.ArticlesItem
import com.ilham.data.remote.service.NewsApi
import com.ilham.util.Constans.API_KEY

class SearchPagingSource(
    private val newsApi: NewsApi,
    private val searchQuery: String,
    private val source: String
): PagingSource<Int, ArticlesItem>() {

    private var totalNewCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesItem> {
        val page = params.key ?: 1

        return try {
            val newsResponse = newsApi.searchNews(searchQuery, page, source)
            totalNewCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewCount == newsResponse.totalResults) null else page +1,
                prevKey = null
            )
        } catch (e: Exception){
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, ArticlesItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}