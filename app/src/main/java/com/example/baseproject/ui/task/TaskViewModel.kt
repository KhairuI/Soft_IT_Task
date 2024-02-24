package com.example.baseproject.ui.task

import com.example.baseproject.ui.base.BaseViewModel
import com.example.baseproject.ui.task.delegate.TaskViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskViewModelDelegate: TaskViewModelDelegate
) : BaseViewModel(), TaskViewModelDelegate by taskViewModelDelegate