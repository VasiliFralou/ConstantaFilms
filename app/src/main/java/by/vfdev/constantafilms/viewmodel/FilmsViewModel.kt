package by.vfdev.constantafilms.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.constantafilms.remotemodel.Films
import by.vfdev.constantafilms.repository.FilmsRepository
import by.vfdev.constantafilms.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(
    private val filmsRepository: FilmsRepository) : ViewModel() {

    private val _selectFilmsLD = MutableLiveData<Films>()
    val selectFilmsLD: LiveData<Films> = _selectFilmsLD

    private val _onSelectFilmsEvent = SingleLiveEvent<Unit?>()
    val onSelectFilmsEvent: LiveData<Unit?> = _onSelectFilmsEvent

    fun onSelectFilms(film: Films) {
        _selectFilmsLD.value = film
        _onSelectFilmsEvent.call()
    }

    val filmsLive: MutableLiveData<MutableList<Films>> by lazy {
        MutableLiveData<MutableList<Films>>()
    }

    fun getListFilms() {
        viewModelScope.launch {
            val list = filmsRepository.getDataFilms()
            list.onSuccess {
                filmsLive.value = it
            }.onFailure {
                filmsLive.value = null
            }
        }
    }
}