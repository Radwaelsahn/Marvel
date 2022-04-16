package com.marvel.radwa.di


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.marvel.radwa.BuildConfig
import com.marvel.radwa.data.source.remote.repositories.characters.CharactersService
import com.marvel.radwa.data.source.remote.repositories.comics.CharacterComicsService
import com.marvel.radwa.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }


    @Singleton
    @Provides
    fun OkHttpClient(
        @AuthInterceptorQualifier authInterceptor: Interceptor,
        @TokenAuthenticatorQualifier tokenAuthenticator: Authenticator
    ): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = //if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
//        else HttpLoggingInterceptor.Level.NONE

        return OkHttpClient().newBuilder().apply {
            connectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(authInterceptor)
            authenticator(tokenAuthenticator)
            addInterceptor(loggingInterceptor)
        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gson: Gson, okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder().client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL + BuildConfig.API_VERSION)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }


    @Singleton
    @Provides
    fun provideCharactersService(retrofitBuilder: Retrofit.Builder): CharactersService = retrofitBuilder.build().create(
        CharactersService::class.java)

    @Singleton
    @Provides
    fun provideComicsService(retrofitBuilder: Retrofit.Builder): CharacterComicsService = retrofitBuilder.build().create(
        CharacterComicsService::class.java)


//    @Singleton
//    @Provides
//    fun provideAccountService(retrofitBuilder: Retrofit.Builder): AccountService =
//        retrofitBuilder.build().create(AccountService::class.java)


}