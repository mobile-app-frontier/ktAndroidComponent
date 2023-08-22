package com.example.bannerexampleapp.ui.features.start

import com.example.bannerexampleapp.core.base.ViewState
import com.kt.basickit.banner.domain.entity.BannerPolicy

sealed class StartState : ViewState() {
    object Loading : StartState()
    object FailToInitialize : StartState()
    data class Success(val bannerPolicy: BannerPolicy) : StartState()
}