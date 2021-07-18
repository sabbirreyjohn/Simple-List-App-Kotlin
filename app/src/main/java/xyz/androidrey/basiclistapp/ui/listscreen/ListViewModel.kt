package xyz.androidrey.basiclistapp.ui.listscreen

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.androidrey.basiclistapp.datasource.PictureApi
import xyz.androidrey.basiclistapp.datasource.PicturesDatabase
import xyz.androidrey.basiclistapp.datasource.getDatabase
import xyz.androidrey.basiclistapp.model.Picture
import xyz.androidrey.basiclistapp.repository.PictureRepository
import java.io.IOException
import java.lang.Exception

enum class ApiStatus { LOADING, DONE, ERROR }
class ListViewModel(application: Application) : AndroidViewModel(application) {

    val repo = PictureRepository(getDatabase(application))

    var pictures = repo.getPicsFromDB()

//    private val _pictures = MutableLiveData<List<Picture>>()
//    val pictures: LiveData<List<Picture>> get() = _pictures

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> get() = _status

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val tempPics = repo.getPicsFromServer()
                repo.insertPicsToDB(tempPics)
                _status.value=ApiStatus.DONE

            } catch (networkError: IOException) {
                networkError.printStackTrace()
                _status.value = ApiStatus.ERROR
            }
        }
    }

}