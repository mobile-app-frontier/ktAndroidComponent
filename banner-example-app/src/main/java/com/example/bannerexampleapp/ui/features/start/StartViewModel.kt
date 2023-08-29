package com.example.bannerexampleapp.ui.features.start

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.example.bannerexampleapp.BuildConfig
import com.example.bannerexampleapp.core.base.StateViewModel
import com.example.bannerexampleapp.core.datastore.PreferenceDataStore
import com.example.bannerexampleapp.core.logger.Logger
import com.example.bannerexampleapp.data.source.BannerPolicyDataSourceImpl
import com.kt.basickit.banner.LocalBannerPolicy
import com.kt.basickit.banner.fetcher.BannerPolicyFetcher
import com.kt.basickit.banner.fetcher.BannerPolicyState
import com.kt.basickit.banner.domain.entity.BannerPolicy
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.reflect.Type
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    @ApplicationContext application: Context,
    private val preferenceDataStore: PreferenceDataStore,
    dataSource: BannerPolicyDataSourceImpl
) : StateViewModel<StartState>(initialState = StartState.Loading) {
    private val bannerPolicyFetcher = BannerPolicyFetcher(
        context = application.applicationContext,
        dataSource = dataSource,
        localBannerPolicyGetter = { return@BannerPolicyFetcher readLocalBannerPolicy() },
        localBannerPolicySetter = { saveLocalBannerPolicy(localBannerPolicy = it) },
        appVersion = BuildConfig.VERSION_NAME,
    )

    init {
        viewModelScope.launch {
            bannerPolicyFetcher.state.collect {
                when (it) {
                    is BannerPolicyState.Success -> updateState { StartState.Success(it.willShowBanner) }
                    is BannerPolicyState.Fail -> updateState { StartState.Success(BannerPolicy.create()) }
                    else -> {
                        // do nothing
                    }
                }
            }
        }
    }

    fun fetchInitialData() {
        Logger.d("fetch initial data")

        viewModelScope.launch {
            try {
                bannerPolicyFetcher.fetch()
            } catch (e: Exception) {
                Logger.e("fetching error: ${e.message}")
                updateState { StartState.FailToInitialize }
            }
        }
    }

    suspend fun saveLocalBannerPolicy(localBannerPolicy: LocalBannerPolicy) {
        Logger.d("LocalBannerPolicy write ${localBannerPolicyToJsonString(localBannerPolicy = localBannerPolicy)}")

        preferenceDataStore.updateLocalBannerPolicy(localBannerPolicyToJsonString(localBannerPolicy = localBannerPolicy))
    }

    suspend fun readLocalBannerPolicy(): LocalBannerPolicy {
        val jsonString = preferenceDataStore
            .getLocalBannerPolicy()
            .first()

        Logger.d("LocalBannerPolicy read ${jsonStringToLocalBannerPolicy(jsonString)}")
        return jsonStringToLocalBannerPolicy(jsonString) ?: mapOf()
    }

    private fun localBannerPolicyToJsonString(localBannerPolicy: LocalBannerPolicy): String {
        val moshi = Moshi.Builder().build()
        val type: Type = Types.newParameterizedType(Map::class.java, String::class.java, String::class.java)
        val adapter: JsonAdapter<Map<String, String>> = moshi.adapter(type)

        return adapter.toJson(localBannerPolicy)
    }

    private fun jsonStringToLocalBannerPolicy(jsonString: String): LocalBannerPolicy? {
        if (jsonString.isEmpty()) {
            return null
        }

        val moshi = Moshi.Builder().build()
        val type: Type = Types.newParameterizedType(Map::class.java, String::class.java, String::class.java)
        val adapter: JsonAdapter<Map<String, String>> = moshi.adapter(type)
        return adapter.fromJson(jsonString)
    }
}