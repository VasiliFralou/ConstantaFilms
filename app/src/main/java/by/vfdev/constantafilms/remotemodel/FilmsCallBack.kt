package by.vfdev.constantafilms.remotemodel

import com.google.gson.annotations.SerializedName

data class FilmsCallBack(
    @SerializedName("items")
    var results: MutableList<Films>
)