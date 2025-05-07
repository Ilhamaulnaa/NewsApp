package com.ilham.newsapp.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilham.data.remote.dto.ArticlesItem
import com.ilham.domain.usecase.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
        private set

    private val _state = mutableStateOf(DetailState())

    fun onEvent(event: DetailEvent){
        when(event){
            is DetailEvent.UpsertDeleteArticleItem -> {
                viewModelScope.launch {
                    val article = newsUseCases.selectArticle(event.article.url)
                    if (article == null){
                        upsertArticle(event.article)
                    } else {
                        deleteArticle(event.article)
                    }
                }
            }
            is DetailEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private suspend fun deleteArticle(article: ArticlesItem) {
        newsUseCases.deleteAtticle(articlesItem = article)
        sideEffect = "Article Deleted"
    }

    private suspend fun upsertArticle(article: ArticlesItem) {
        newsUseCases.upsertArticle(articlesItem = article )
        sideEffect = "Article Saved"
    }

}