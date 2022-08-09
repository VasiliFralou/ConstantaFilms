package by.vfdev.constantafilms.remotemodel

data class Films(
    val actors: List<Actor> = arrayListOf(),
    val directorName: String? = null,
    val releaseYear: Int? = null,
    val title: String? = null
)