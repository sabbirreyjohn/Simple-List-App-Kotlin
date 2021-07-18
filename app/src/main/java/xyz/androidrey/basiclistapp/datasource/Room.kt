package xyz.androidrey.basiclistapp.datasource

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import xyz.androidrey.basiclistapp.model.Picture

@Dao
interface PictureDao {
    @Query("select * from Picture")
    fun getPictures(): LiveData<List<Picture>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pictures: List<Picture>)
}

@Database(entities = [Picture::class], version = 1)
abstract class PicturesDatabase : RoomDatabase() {
    abstract val pictureDao: PictureDao
}

private lateinit var INSTANCE: PicturesDatabase

fun getDatabase(context: Context): PicturesDatabase {
    synchronized(PicturesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                PicturesDatabase::class.java,
                "pictures"
            ).build()
        }
    }
    return INSTANCE
}