package xyz.androidrey.basiclistapp.ui.listscreen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import xyz.androidrey.basiclistapp.MainCoroutineRule
import xyz.androidrey.basiclistapp.getOrAwaitValue
import xyz.androidrey.basiclistapp.repository.PictureRepository

@RunWith(AndroidJUnit4::class)
@Config(manifest= Config.NONE)
class ListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var listViewModel : ListViewModel

    private lateinit var mRepository: PictureRepository


    @Before
    fun setUp(){

        mRepository = mockk<PictureRepository>()
        listViewModel = ListViewModel(ApplicationProvider.getApplicationContext(), mRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadData_loadsDataHappyPath(){
        mainCoroutineRule.runBlockingTest {
            listViewModel.loadData()
            assertNotEquals(listViewModel.pictures.getOrAwaitValue()?.size, 0)
        }
    }
}
