package io.igaryhe.yabc

import androidx.lifecycle.LiveData
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface BgmService {

    @GET("/user/{user_id}/collection")
    fun getUserCollection(@Path("user_id") userId: Int,
                          @Query("cat") cat: String): LiveData<List<CollectionSubject>>

    @Headers("Accept: application/json")
    @POST("https://bgm.tv/oauth/access_token")
    @FormUrlEncoded
    fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String,
        @Field("grant_type") grantType: String
    ): LiveData<AccessToken>

    @Headers("Accept: application/json")
    @POST("https://bgm.tv/oauth/access_token")
    @FormUrlEncoded
    fun refreshToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("refresh_token") refreshToken: String,
        @Field("redirect_uri") redirectUri: String,
        @Field("grant_type") grantType: String
    ): LiveData<RefreshToken>

    companion object Factory {
        fun create(): BgmService {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .baseUrl("https://api.bgm.tv")
                .build()
                .create(BgmService::class.java)
        }
    }
}