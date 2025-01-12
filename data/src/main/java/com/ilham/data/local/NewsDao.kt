package com.ilham.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ilham.data.remote.dto.ArticlesItem
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsets(articlesItem: ArticlesItem)

    @Delete
    suspend fun delete(articlesItem: ArticlesItem)

    @Query("SELECT * FROM ArticlesItem")
    fun getArticlles(): Flow<List<ArticlesItem>>

}




