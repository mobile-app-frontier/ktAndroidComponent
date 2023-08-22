package com.example.bannerexampleapp.di

import com.example.bannerexampleapp.data.source.BannerPolicyDataSourceImpl
import com.example.bannerexampleapp.domain.usecase.BannerPolicyUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class MainModule {
    @ViewModelScoped
    @Provides
    fun provideBannerPolicyUseCase(
        dataSource: BannerPolicyDataSourceImpl,
    ): BannerPolicyUseCase {
        return BannerPolicyUseCase(
            dataSource = dataSource
        )
    }
}