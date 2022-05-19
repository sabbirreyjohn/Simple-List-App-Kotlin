package xyz.androidrey.basiclistapp.repository

import xyz.androidrey.basiclistapp.datasource.PictureApiHelper
import xyz.androidrey.basiclistapp.datasource.PicturesDatabase
import xyz.androidrey.basiclistapp.model.Picture
import javax.inject.Inject

class PictureRepository @Inject
    internal constructor(private val database: PicturesDatabase, private val apiHelper : PictureApiHelper) {


    fun getPicsFromDB() = database.pictureDao.getPictures()

    suspend fun getPicsFromServer() = apiHelper.getPictures()

    suspend fun insertPicsToDB(pictures: List<Picture>) = database.pictureDao.insertAll(pictures)

}


