package de.eymers.matthias.jokerapichallenge.di

import android.content.Context
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.eymers.matthias.jokerapichallenge.JokeChallengeApplication
import de.eymers.matthias.jokerapichallenge.core.Constant.BASE_URL
import de.eymers.matthias.jokerapichallenge.data.remote.JokeApi
import de.eymers.matthias.jokerapichallenge.data.repository.JokeRepositoryImpl
import de.eymers.matthias.jokerapichallenge.domain.repository.JokeRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {

    private val readTimeout = 30
    private val writeTimeout = 30
    private val connectionTimeout = 10

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): JokeChallengeApplication {
        return app as JokeChallengeApplication
    }

    @Provides
    @Singleton
    fun provideContext(application: JokeChallengeApplication): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideOkhttpClient(
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(connectionTimeout.toLong(), TimeUnit.SECONDS)
            .readTimeout(readTimeout.toLong(), TimeUnit.SECONDS)
            .writeTimeout(writeTimeout.toLong(), TimeUnit.SECONDS)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    @Singleton
    fun provideJokeApi(retrofit: Retrofit): JokeApi {
        return retrofit.create(JokeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideJokeRepository(api: JokeApi): JokeRepository {
        return JokeRepositoryImpl(api = api)
    }
}