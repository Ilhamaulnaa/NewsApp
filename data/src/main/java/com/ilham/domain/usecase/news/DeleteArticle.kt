package com.ilham.domain.usecase.news

import com.ilham.data.local.NewsDao
import com.ilham.data.remote.dto.ArticlesItem

class DeleteArticle(
    private val newsDao: NewsDao
){

    suspend operator fun invoke(articlesItem: ArticlesItem){
        newsDao.delete(articlesItem)
    }

}