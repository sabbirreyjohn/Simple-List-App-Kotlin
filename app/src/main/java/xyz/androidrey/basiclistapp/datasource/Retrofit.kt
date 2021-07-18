package xyz.androidrey.basiclistapp.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import xyz.androidrey.basiclistapp.model.Picture

private const val BASE_URL =
    "https://jsonplaceholder.typicode.com"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL)
        .build()

interface PictureInterface {
    @GET("photos")
    suspend fun getPictures(): List<Picture>
}

object PictureApi {
    val pictureInterface: PictureInterface by lazy {
        retrofit.create(PictureInterface::class.java)
    }
}