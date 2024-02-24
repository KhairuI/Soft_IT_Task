package com.example.baseproject.ui.task.delegate

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaskViewModelDelegateModule {

    @Provides
    @Singleton
    internal fun provideTaskViewModelDelegate(taskViewModelDelegateImpl: TaskViewModelDelegateImpl): TaskViewModelDelegate =
        taskViewModelDelegateImpl
}