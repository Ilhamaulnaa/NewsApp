package com.ilham.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ilham.data.remote.dto.ArticlesItem
import com.ilham.data.remote.service.NewsApi

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val source: String
): PagingSource<Int, ArticlesItem>() {

    private var totalNewCount = 0

    //Specifies the key used to reload data when the dataset changes.
    override fun getRefreshKey(state: PagingState<Int, ArticlesItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    //This function must be implemented to determine how the data is loaded.
    //LoadParams: Berisi parameter seperti loadSize (jumlah data yang akan dimuat) dan key (posisi data saat ini).
    //LoadResult: Hasil dari permintaan data, bisa berupa:
    //LoadResult.Page: Berhasil memuat data.
    //LoadResult.Error: Gagal memuat data.
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesItem> {

        val page = params.key ?: 1

        return try {
            val newsResponse = newsApi.getNews(page = page, source = source)
            totalNewCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title } // remove duplicate
            PagingSource.LoadResult.Page(
                data = articles,
                nextKey = if (totalNewCount == newsResponse.totalResults) null else + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            PagingSource.LoadResult.Error(
                throwable = e
            )
        }

    }

}