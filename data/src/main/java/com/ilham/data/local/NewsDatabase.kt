package com.ilham.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ilham.data.remote.dto.ArticlesItem

@Database(entities = [ArticlesItem::class], version = 1)
@TypeConverters(NewsTypeConvertor::class)
abstract class NewsDatabase: RoomDatabase() {

    abstract val newsDao: NewsDao

}