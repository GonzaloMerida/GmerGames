package com.example.gmergames.data

import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("summary") val summary: String? = null,
    @SerializedName("genres")val genres: List<Int>? = null,
    @SerializedName("screenshots") val screenshots: List<Int>? = null
) {
    fun toItem(): Item {
//        val genreGame : String = Genre.name
//        val urlScreenShot : String = Screenshot.url
        return Item(
            id = id,
            name = name,
            summary = summary ?: "",
            genre = genres?.get(0)?.toString() ?: "",
            photo = screenshots?.get(0)?.toString() ?: ""
        )
    }
}

data class Genre(
    @SerializedName("id") val id: Int,
    @SerializedName("created_at") val createdAt: Long,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("updated_at") val updatedAt: Long,
    @SerializedName("url") val url: String,
    @SerializedName("checksum") val checksum: String
)


data class Screenshot(
    @SerializedName("id") val id: Int,
    @SerializedName("alpha_channel") val alphaChannel: Boolean? = null,
    @SerializedName("animated") val animated: Boolean? = null,
    @SerializedName("game") val game: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("image_id") val imageId: String,
    @SerializedName("url") val url: String,
    @SerializedName("width") val width: Int,
    @SerializedName("checksum") val checksum: String
) {
    companion object {
//        val _url: String
    }
}

