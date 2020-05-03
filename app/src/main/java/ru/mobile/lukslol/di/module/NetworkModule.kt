package ru.mobile.lukslol.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
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