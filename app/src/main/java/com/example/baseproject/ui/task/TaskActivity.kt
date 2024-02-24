package com.example.baseproject.ui.task

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.baseproject.data.network.request.LoginRequest
import com.example.baseproject.databinding.ActivityTaskBinding
import com.example.baseproject.ui.base.BaseActivity
import com.example.baseproject.utils.arch.Result
import com.example.baseproject.utils.extension.loading
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.json.JSONObject

@AndroidEntryPoint
class TaskActivity : BaseActivity() {

    private val viewModel by viewModels<TaskViewModel>()

    private lateinit var binding: ActivityTaskBinding

    companion object {
        fun getStartIntent(context: Context): Intent =
            Intent(context, TaskActivity::class.java)
    }

    override fun getLayoutResourceId(): View {
        binding = ActivityTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        binding.btnLogin.setOnClickListener {
            viewModel.login(
                LoginRequest(
                    binding.etPhone.text.toString(),
                    binding.etPassword.text.toString()
                )
            )
        }

    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.loginResponse.collect { result ->
                        when (result) {
                            is Result.Error -> {
                                Log.d("xxx", "Error: ${result.uiText.asString(this@TaskActivity)}")

                                val errorBody = result.uiText.asString(this@TaskActivity)
                                val errorJson = JSONObject(errorBody.trim())
                                val errorMessage = errorJson.getJSONObject("error")

                                if (errorMessage.has("phone")) test(errorMessage.get("phone").toString())
                                else test(errorMessage.get("password").toString())
                            }

                            is Result.Loading -> {
                                loading(result.isLoading)
                            }

                            is Result.Success -> {
                                Toast.makeText(
                                    this@TaskActivity,
                                    "${result.data.message}",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        }
                    }
                }
            }
        }

    }

    private fun test(text: String) {
        Toast.makeText(
            this@TaskActivity,
            text.replace("\"", "").replace("[", "").replace("]", ""),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun dev() {}
}