package com.farmaciasperuanas.dermaapp.api

import com.farmaciasperuanas.dermaapp.base.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ServiceFactory {

    companion object {
        @Volatile
        private var INSTANCE: DermaService? = null

        // private const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/"
        private const val BASE_URL = "http://193.178.0.96:8080/"

        fun getService(): DermaService {
            return INSTANCE ?: synchronized(this) {
                val instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(provideOkHttpClient())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(LiveDataCallAdapterFactory())
                    .build()
                    .create(DermaService::class.java)
                INSTANCE = instance
                instance
            }
        }

        fun <T> obtenerService(serviceClass: Class<T>): T {
            return serviceClass.newInstance() ?: synchronized(this) {
                val instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(provideOkHttpClient())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(LiveDataCallAdapterFactory())
                    .build()
                    .create(serviceClass)
                return instance
            }
        }

        private fun provideOkHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
            client.addInterceptor(interceptor)
            client.retryOnConnectionFailure(true)
            return client.build()

            /*val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor { chain ->
                val original = chain.request()

                // Request customization: add request headers
                val requestBuilder = original.newBuilder()
                    //.header("Authorization", "MY_API_KEY") // <-- this is the important line
                    .header("Connection", "close")

                val request = requestBuilder.build()
                chain.proceed(request)
            }


            httpClient.connectTimeout(30, TimeUnit.SECONDS)
            httpClient.readTimeout(30, TimeUnit.SECONDS)

            httpClient.addNetworkInterceptor(logging)

            val okHttpClient=httpClient.build()
            return okHttpClient*/
        }
    }
}
