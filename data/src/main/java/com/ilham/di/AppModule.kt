package com.ilham.di

import android.app.Application
import androidx.room.Room
import com.ilham.data.local.NewsDao
import com.ilham.data.local.NewsDatabase
import com.ilham.data.local.NewsTypeConvertor
import com.ilham.data.manger.LocalUserMangerImpl
import com.ilham.data.remote.repository.NewsRepositoryImpl
import com.ilham.data.remote.service.NewsApi
import com.ilham.domain.manger.LocalUserManger
import com.ilham.domain.repository.NewsRepository
import com.ilham.domain.usecase.app_entry.AppEntryUseCases
import com.ilham.domain.usecase.app_entry.ReadAppEntry
import com.ilham.domain.usecase.app_entry.SaveAppEntry
import com.ilham.domain.usecase.news.GetNews
import com.ilham.domain.usecase.news.NewsUseCases
import com.ilham.domain.usecase.news.SearchNews
import com.ilham.util.Constans.BASE_URL
import com.ilham.util.Constans.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManger = LocalUserMangerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(
        newsApi = newsApi
    )

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(
                newsRepository = newsRepository,
            ),
            searchNews = SearchNews(
                newsRepository = newsRepository
            )
        )
    }

    @Provides
    @Singleton
    fun NewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

}