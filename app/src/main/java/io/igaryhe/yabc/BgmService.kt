package io.igaryhe.yabc

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface BgmService {

    @GET("/user/{user_id}/collection")
    fun getUserCollection(@Path("user_id") userId: Int,
                          @Query("cat") cat: String): Observable<List<CollectionSubject>>

    companion object Factory {
        fun create(): BgmService {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://api.bgm.tv")
                .build()
                .create(BgmService::class.java)
        }
    }
}