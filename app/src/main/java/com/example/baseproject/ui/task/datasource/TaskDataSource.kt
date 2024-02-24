package com.example.baseproject.ui.task.datasource

import android.content.Context
import com.example.baseproject.R
import com.example.baseproject.data.network.request.LoginRequest
import com.example.baseproject.data.network.response.LoginResponse
import com.example.baseproject.data.network.service.ApiServiceJobTask
import com.example.baseproject.di.ext.IoDispatcher
import com.example.baseproject.ui.base.datasource.NetworkHandlerDataSource
import com.example.baseproject.utils.arch.Result
import com.example.baseproject.utils.arch.UiText
import com.example.baseproject.utils.extension.isNetworkConnected
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface TaskDataSource {
    suspend fun login(request: LoginRequest): Flow<Result<LoginResponse>>
}

class TaskDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val apiServiceJobTask: ApiServiceJobTask,
    private val networkHandlerDataSource: NetworkHandlerDataSource
) : TaskDataSource {

    private fun hasInternetConnection(): Boolean = context.isNetworkConnected()

    override suspend fun login(request: LoginRequest): Flow<Result<LoginResponse>> = flow {
        try {

            if (!hasInternetConnection()) {
                emit(Result.Error(UiText.StringResource(R.string.http_no_internet)))
                return@flow
            }
            emit(Result.Loading())

            val response = apiServiceJobTask.sendLogin(request)
            if (!response.isSuccessful) {
                response.errorBody()?.string().let { errorMsg ->
                    emit(Result.Error(UiText.DynamicString(errorMsg)))
                    return@flow
                }
            }

            response.body()?.let { data ->
                emit(Result.Success(data))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(networkHandlerDataSource.handleException(e)))
        } finally {
            emit(Result.Loading(false))
        }
    }.flowOn(ioDispatcher)
}