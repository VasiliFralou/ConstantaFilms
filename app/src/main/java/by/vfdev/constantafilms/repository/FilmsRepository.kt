package by.vfdev.constantafilms.repository

import android.util.Log
import by.vfdev.constantafilms.remotemodel.Films
import by.vfdev.constantafilms.remotemodel.RemoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilmsRepository @Inject constructor(
    private val filmsRemoteModel: RemoteModel) {

    suspend fun getDataFilms():
            Result<MutableList<Films>> = withContext(Dispatchers.IO) {

        val filmsItem = filmsRemoteModel.getFilmsRemoteModel()

        return@withContext Result.success(filmsItem)
    }
}