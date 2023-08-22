package com.example.bannerexampleapp.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PreferenceDataStore(
    private val context: Context,
    private val dispatcher: CoroutineDispatcher,
) {

    private object PrefConstants {
        const val storeKey = "preferences"
        val localBannerPolicy = stringPreferencesKey("local_banner_policy")
    }

    private val Context.dataStore by preferencesDataStore(
        name = PrefConstants.storeKey,
    )

    suspend fun updateLocalBannerPolicy(localBannerPolicy: String) {
        CoroutineScope(dispatcher).launch {
            context.dataStore.edit {
                it[PrefConstants.localBannerPolicy] = localBannerPolicy
            }
        }
    }

    fun getLocalBannerPolicy(): Flow<String> {
        return context.dataStore.data.map {
            it[PrefConstants.localBannerPolicy] ?: ""
        }
    }
}
