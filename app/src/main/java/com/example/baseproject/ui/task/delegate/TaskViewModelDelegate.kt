package com.example.baseproject.ui.task.delegate

import com.example.baseproject.data.network.request.LoginRequest
import com.example.baseproject.data.network.response.LoginResponse
import com.example.baseproject.di.ext.ApplicationScope
import com.example.baseproject.ui.task.datasource.TaskDataSource
import com.example.baseproject.utils.arch.Result
import com.example.baseproject.utils.arch.tryOffer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface TaskViewModelDelegate {
    fun login(request: LoginRequest)
    val loginResponse: Flow<Result<LoginResponse>>
}

internal class TaskViewModelDelegateImpl @Inject constructor(
    @ApplicationScope val applicationScope: CoroutineScope,
    private val taskDataSource: TaskDataSource,
) : TaskViewModelDelegate {

    private val _loginResponse = Channel<Result<LoginResponse>>(Channel.CONFLATED)
    override val loginResponse = _loginResponse.receiveAsFlow()

    override fun login(request: LoginRequest) {
        applicationScope.launch {
            taskDataSource.login(request).collect { result ->
                _loginResponse.tryOffer(result)
            }
        }
    }

}