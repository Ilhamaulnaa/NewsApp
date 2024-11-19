package com.ilham.data.manger

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.ilham.domain.manger.LocalUserManger
import com.ilham.util.Constans
import com.ilham.util.Constans.APP_ENTRY
import com.ilham.util.Constans.USER_SETTING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserMangerImpl(
    private val context: Context
): LocalUserManger {
    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.APP_ENTRY] ?: false
        }
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTING )

private object PreferencesKeys {
    val APP_ENTRY = booleanPreferencesKey(name = Constans.APP_ENTRY)
}