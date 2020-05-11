package ru.mobile.lukslol.service.network

import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.http.*
import ru.mobile.lukslol.service.network.dto.NetworkPost
import ru.mobile.lukslol.service.network.response.AuthResponse

interface NetworkManager {

    @FormUrlEncoded
    @POST("v1/auth/users")
    fun getSummoner(@Field("name") name: String,
                    @Field("region") region: String): Deferred<AuthResponse>


    @GET("v1/feed")
    fun getPosts(@Query("limit") limit: Int,
                 @Query("offset") offset: Int): Deferred<List<NetworkPost>>
}