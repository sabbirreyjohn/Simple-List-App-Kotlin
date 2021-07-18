package xyz.androidrey.basiclistapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import xyz.androidrey.basiclistapp.datasource.PictureApi
import xyz.androidrey.basiclistapp.datasource.PicturesDatabase
import xyz.androidrey.basiclistapp.model.Picture

class PictureRepository(private val database: PicturesDatabase) {


    fun getPicsFromDB() = database.pictureDao.getPictures()

    suspend fun getPicsFromServer() = PictureApi.pictureInterface.getPictures()

    suspend fun insertPicsToDB(pictures: List<Picture>) = database.pictureDao.insertAll(pictures)

}


