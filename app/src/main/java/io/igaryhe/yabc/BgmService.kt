package io.igaryhe.yabc

import androidx.lifecycle.LiveData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface BgmService {

    @GET("/user/{user_id}/collection")
    fun getUserCollection(@Path("user_id") userId: Int,
                          @Query("cat") cat: String): LiveData<List<CollectionSubject>>

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