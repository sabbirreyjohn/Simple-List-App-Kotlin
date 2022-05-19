package xyz.androidrey.basiclistapp.ui.listscreen

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xyz.androidrey.basiclistapp.repository.PictureRepository
import java.io.IOException

enum class ApiStatus { LOADING, DONE, ERROR }

class ListViewModel
@ViewModelInject constructor(
    application: Application,
    private val repository: PictureRepository
) : AndroidViewModel(application) {

    var pictures = repository.getPicsFromDB()

    private val _status = MutableLiveData<ApiStatus?>()
    val status: LiveData<ApiStatus?> get() = _status

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            _status.postValue(ApiStatus.LOADING)
            try {
                val tempPics = repository.getPicsFromServer()
                repository.insertPicsToDB(tempPics ?: emptyList())
                _status.postValue(ApiStatus.DONE)

            } catch (networkError: IOException) {
                networkError.printStackTrace()
                _status.postValue(ApiStatus.ERROR)
            }
        }
    }


}