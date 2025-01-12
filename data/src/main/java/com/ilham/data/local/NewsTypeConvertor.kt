package com.ilham.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.ilham.data.remote.dto.ArticlesItem
import com.ilham.data.remote.dto.Source

@ProvidedTypeConverter
class NewsTypeConvertor {

    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.id}"
    }

    @TypeConverter
    fun stringToSource(source: String): Source {
        return source.split(",").let { sourceArray ->
            Source(sourceArray[0], sourceArray[1])
        }
    }

}