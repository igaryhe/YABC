package io.igaryhe.yabc.models

import com.google.gson.annotations.SerializedName

data class AccessToken(@SerializedName("access_token") val accessToken: String,
                       @SerializedName("refresh_token") val refreshToken: String,
                       @SerializedName("user_id") val userId: Int)