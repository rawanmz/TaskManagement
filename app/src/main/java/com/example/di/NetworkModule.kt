package com.example.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.example.data.remote.ArticApi
import com.example.data.remote.FitnessApi
import com.example.data.remote.FitnessCalculatorApi
import com.example.taskmanagement.Constants.ARTIC_BASE_URL
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class AppAPI

@Module(includes = [DataStoreModule::class])
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(
    ): OkHttpClient {
        val httpclient = OkHttpClient.Builder()
            .connectTimeout(5L, TimeUnit.SECONDS)
            .readTimeout(5L, TimeUnit.SECONDS)
            .writeTimeout(5L, TimeUnit.SECONDS)
        return httpclient.build()
    }

    @AppAPI
    @Provides
    fun providePrivateOkHttpClient(
        upstreamClient: OkHttpClient,
        chuckerInterceptor: ChuckerInterceptor,
    ): OkHttpClient {
        val builder = upstreamClient.newBuilder().addInterceptor(chuckerInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    @AppAPI
    fun provideRetrofit(
        @AppAPI okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ARTIC_BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideArticApiService(
        @AppAPI retrofit: Retrofit
    ): ArticApi = retrofit.create(
        ArticApi::class.java
    )

    @Singleton
    @Provides
    fun provideFitnessApiService(
        @AppAPI retrofit: Retrofit
    ): FitnessApi = retrofit.create(
        FitnessApi::class.java
    )

    @Singleton
    @Provides
    fun provideFitnessCalculatorApiService(
        @AppAPI retrofit: Retrofit
    ): FitnessCalculatorApi = retrofit.create(
        FitnessCalculatorApi::class.java
    )

    @Singleton
    @Provides
    fun createCollector(@ApplicationContext context: Context): ChuckerInterceptor {
        // Create the Collector
        val chuckerCollector = ChuckerCollector(
            context = context,
            // Toggles visibility of the notification
            showNotification = true,
            // Allows to customize the retention period of collected data
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )

        // Create the Interceptor
        return ChuckerInterceptor.Builder(context)
            // The previously created Collector
            .collector(chuckerCollector)
            // The max body content length in bytes, after this responses will be truncated.
            .maxContentLength(250_000L)
            // List of headers to replace with ** in the Chucker UI
            .redactHeaders("Auth-Token", "Bearer")
            // Read the whole response body even when the client does not consume the response completely.
            // This is useful in case of parsing errors or when the response body
            // is closed before being read like in Retrofit with Void and Unit types.
            .alwaysReadResponseBody(true)
            // Use decoder when processing request and response bodies. When multiple decoders are installed they
            // are applied in an order they were added.
//        .addBodyDecoder(decoder)
            // Controls Android shortcut creation. Available in SNAPSHOTS versions only at the moment
//        .createShortcut(true)
            .build()

        // Don't forget to plug the ChuckerInterceptor inside the OkHttpClient
    }
}