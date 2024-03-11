package com.example.gmergames.data

import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("screenshots") val screenshots: List<Screenshot>,
    @SerializedName("platforms") val platforms: List<Platform>
) {
    fun toItem(): Item {
        return Item(
            id = id,
            name = name,
            summary = summary,
            genre = genres,
            photo = screenshots.get(0).toString(),
            platform = platforms
        )
    }
}

data class Genre(
    @SerializedName("id") val id: Int
)

data class Platform(
    @SerializedName("id") val id: Int
)

data class Screenshot(
    @SerializedName("id") val id: Int
)

data class

