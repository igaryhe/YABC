package io.igaryhe.yabc.entities

import com.google.gson.annotations.SerializedName

data class RefreshToken(@SerializedName("access_token") val accessToken: String,
                        @SerializedName("refresh_token") val refreshToken: String)