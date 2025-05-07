package com.ilham.newsapp.presentation.detail

import com.ilham.data.remote.dto.ArticlesItem

sealed class DetailEvent{

    data class UpsertDeleteArticleItem(val article: ArticlesItem): DetailEvent()

    object RemoveSideEffect: DetailEvent()

}
