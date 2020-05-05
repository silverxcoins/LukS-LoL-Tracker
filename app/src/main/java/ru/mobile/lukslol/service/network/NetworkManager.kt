package ru.mobile.lukslol.service.network

import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import ru.mobile.lukslol.service.network.response.AuthResponse

interface NetworkManager {

    @FormUrlEncoded
    @POST("v1/auth/users")
    fun getSummoner(@Field("name") name: String,
                    @Field("region") region: String): Deferred<AuthResponse>
}