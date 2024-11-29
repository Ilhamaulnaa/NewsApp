package com.ilham.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilham.domain.usecase.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel(){

    val news = newsUseCases.getNews(
        sources = listOf("BBC-News", "Al-jazeera English", "Eventhings")
    ).launchIn(viewModelScope)

}