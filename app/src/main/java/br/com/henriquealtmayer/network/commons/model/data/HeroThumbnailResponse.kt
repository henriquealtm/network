package br.com.henriquealtmayer.network.commons.model.data

import com.google.gson.annotations.SerializedName

data class HeroThumbnailResponse(
    @SerializedName("path") val path: String?,
    @SerializedName("extension") val extension: String?
) {
    fun getUrl(): String {
        return path?.replace("http", "https") + "." + extension
    }
}