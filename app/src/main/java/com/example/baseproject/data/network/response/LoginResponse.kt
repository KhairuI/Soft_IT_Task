package com.example.baseproject.data.network.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @Expose @SerializedName("data") var data: Data? =  null,
    @Expose @SerializedName("message") var message: String? = null,
    @Expose @SerializedName("status") var status: Boolean? = null
) {
    data class Data(
        @Expose @SerializedName("access_token") var access_token: String? = null,
        @Expose @SerializedName("user") var user: User? = null,
    ){
        data class User(
            @Expose @SerializedName("id") var id: Int? = null,
            @Expose @SerializedName("name") var name: String? = null,
            @Expose @SerializedName("phone") var phone: String? = null
        )
    }
}