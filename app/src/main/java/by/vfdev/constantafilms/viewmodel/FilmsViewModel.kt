package by.vfdev.constantafilms.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.constantafilms.remotemodel.Films
import by.vfdev.constantafilms.repository.FilmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(
    private val filmsRepository: FilmsRepository) : ViewModel() {

    private val filmsLive: MutableLiveData<MutableList<Films>> by lazy {
        MutableLiveData<MutableList<Films>>()
    }

    fun getListFilms() {
        viewModelScope.launch {
            val list = filmsRepository.getDataFilms()
            list.onSuccess {
                filmsLive.value = it
                Log.e("!!!", it.toString())
            }.onFailure {
                filmsLive.value = null
            }
        }
    }
}