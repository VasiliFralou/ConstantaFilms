package by.vfdev.constantafilms.remotemodel

import by.vfdev.constantafilms.api.ApiFilms
import java.lang.Exception
import javax.inject.Inject

class RemoteModel @Inject constructor() {

    private val apiFilms = ApiFilms.create()

    suspend fun getFilmsRemoteModel() : MutableList<Films> {
        return try {

            val films = apiFilms.getFilms().results
            films
        } catch (e: Exception) {
            mutableListOf()
        }
    }
}