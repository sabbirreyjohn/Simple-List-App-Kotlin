package xyz.androidrey.basiclistapp

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import xyz.androidrey.basiclistapp.datasource.*
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun getContext() = BaseApplication.instance


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        return Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
            "https://jsonplaceholder.typicode.com"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = getDatabase(context)

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(PictureInterface::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: PictureApiHelperImpl): PictureApiHelper = apiHelper

    @Provides
    fun provideSubmissionDao(database: PicturesDatabase): PictureDao {
        return database.pictureDao
    }
}