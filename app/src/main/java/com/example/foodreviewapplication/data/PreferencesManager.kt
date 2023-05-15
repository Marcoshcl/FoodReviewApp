package com.example.foodreviewapplication.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext

enum class SortOrder { BY_NAME, BY_DATE }

data class FilterPreference(val sortOrder: SortOrder)

@Singleton
class PreferencesManager @Inject constructor(@ApplicationContext private val context : Context) {

    private val Context.dataStore by preferencesDataStore("user_preferences")
    val preferenceFlow = context.dataStore.data
        .map{preferences ->
            val sortOrder = SortOrder.valueOf(
                preferences[PreferencesKeys.SORT_ORDER] ?:
                    SortOrder.BY_DATE.name)
            FilterPreference(sortOrder)
        }

    suspend fun updateSortOrderCompleted(sortOrder: SortOrder) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.SORT_ORDER] = sortOrder.name
        }
    }
    private object PreferencesKeys {
        val SORT_ORDER = stringPreferencesKey("sort_order")
    }
}