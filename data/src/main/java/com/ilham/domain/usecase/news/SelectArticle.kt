package com.ilham.domain.usecase.news

import com.ilham.data.local.NewsDao
import com.ilham.data.remote.dto.ArticlesItem
import kotlinx.coroutines.flow.Flow

class SelectArticle(
    private val newsDao: NewsDao
) {

    operator fun invoke(): Flow<List<ArticlesItem>>{
        return newsDao.getArticlles()
    }

}