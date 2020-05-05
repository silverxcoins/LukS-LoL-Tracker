package ru.mobile.lukslol.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.mobile.lukslol.service.network.NetworkManager
import ru.mobile.lukslol.util.Environment
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        private const val CONNECT_TIMEOUT_SECONDS = 5L
    }

    @Provides
    @Singleton
    fun provideNetworkManager(gson: Gson, client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(Environment.getHost())
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(NetworkManager::class.java)

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder()
        .serializeNulls()
        .create()

    @Provides
    @Singleton
    fun provideClient() = OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

}