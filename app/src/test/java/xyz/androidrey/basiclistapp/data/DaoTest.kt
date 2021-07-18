package xyz.androidrey.basiclistapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import xyz.androidrey.basiclistapp.MainCoroutineRule
import xyz.androidrey.basiclistapp.datasource.PicturesDatabase
import xyz.androidrey.basiclistapp.getOrAwaitValue
import xyz.androidrey.basiclistapp.model.Picture


@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class DaoTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: PicturesDatabase
    private lateinit var pictures: List<Picture>

    @Before
    fun initDb() {
        // Using an in-memory database so that the information stored here disappears when the
        // process is killed.

        pictures = listOf<Picture>(
            Picture("1", "Title 1", "http://Pic", "http://thumbnail"),
            Picture("2", "Title 2", "http://Pic", "http://thumbnail"),
            Picture("3", "Title 3", "http://Pic", "http://thumbnail")
        )
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            PicturesDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun `insert pictures succeessfully`() {

        runBlockingTest {
            database.pictureDao.insertAll(pictures)

            val gotPic = database.pictureDao.getPictures()

            assertThat(gotPic.getOrAwaitValue().size).isEqualTo(3)
        }
    }

    @Test
    fun `replace pictures if already exists`() {
        runBlockingTest {
            database.pictureDao.insertAll(pictures)
            database.pictureDao.insertAll(pictures)

            val gotPic = database.pictureDao.getPictures().getOrAwaitValue()

            assertThat(gotPic.size).isEqualTo(3)
        }
    }
}