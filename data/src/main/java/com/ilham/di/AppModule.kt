package com.ilham.di

import android.app.Application
import com.ilham.data.manger.LocalUserMangerImpl
import com.ilham.domain.manger.LocalUserManger
import com.ilham.domain.usecase.app_entry.AppEntryUseCases
import com.ilham.domain.usecase.app_entry.ReadAppEntry
import com.ilham.domain.usecase.app_entry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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

}