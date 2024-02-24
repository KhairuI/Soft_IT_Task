package com.example.baseproject.data.network.request


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @Expose @SerializedName("phone") internal val phone: String? = null,
    @Expose @SerializedName("password") internal val password: String? = null,
)