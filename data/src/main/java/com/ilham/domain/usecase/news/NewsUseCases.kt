package com.ilham.domain.usecase.news

data class NewsUseCases(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val upsertArticle: UpsertArticle,
    val deleteAtticle: DeleteArticle,
    val selectArticle: SelectArticle
)