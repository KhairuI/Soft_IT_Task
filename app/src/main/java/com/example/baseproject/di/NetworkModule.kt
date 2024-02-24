package com.example.baseproject.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.baseproject.data.network.service.ApiServiceJobTask
import com.example.baseproject.di.helper.ApiServiceTaskHelper
import com.example.baseproject.di.helper.ChuckerInterceptorHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {



    @Provides
    @Singleton
    internal fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor =
        ChuckerInterceptorHelper(context).invoke()


    @Provides
    @Singleton
    internal fun provideApiServiceJobTask(chuckerInterceptor: ChuckerInterceptor): ApiServiceJobTask =
        ApiServiceTaskHelper(chuckerInterceptor).invoke()
}