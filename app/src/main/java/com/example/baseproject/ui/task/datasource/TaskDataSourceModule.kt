package com.example.baseproject.ui.task.datasource

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object TaskDataSourceModule {

    @Provides
    @Singleton
    internal fun provideQuoteDataSource(taskDataSourceImpl: TaskDataSourceImpl): TaskDataSource =
        taskDataSourceImpl
}