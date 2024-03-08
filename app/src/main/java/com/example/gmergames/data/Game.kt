package com.example.gmergames.data

import com.google.gson.annotations.SerializedName

data class Game (
    @SerializedName("id") val id: Int,
//    @SerializedName("age_ratings") val ageRatings: List<AgeRating>,
//    @SerializedName("alternative_names") val alternativeNames: List<AlternativeName>,
    @SerializedName("category") val category: Int,
    @SerializedName("cover") val cover: Int,
    @SerializedName("created_at") val createdAt: Long,
    @SerializedName("external_games") val externalGames: List<ExternalGame>,
    @SerializedName("first_release_date") val firstReleaseDate: Long,
    @SerializedName("game_modes") val gameModes: List<GameMode>,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("involved_companies") val involvedCompanies: List<InvolvedCompany>,
    @SerializedName("keywords") val keywords: List<Keyword>,
    @SerializedName("name") val name: String,
    @SerializedName("platforms") val platforms: List<Platform>,
    @SerializedName("player_perspectives") val playerPerspectives: List<PlayerPerspective>,
    @SerializedName("release_dates") val releaseDates: List<ReleaseDate>,
    @SerializedName("screenshots") val screenshots: List<Screenshot>,
    @SerializedName("similar_games") val similarGames: List<SimilarGame>,
    @SerializedName("slug") val slug: String,
    @SerializedName("storyline") val storyline: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("tags") val tags: List<Tag>,
    @SerializedName("themes") val themes: List<Theme>,
    @SerializedName("updated_at") val updatedAt: Long,
    @SerializedName("url") val url: String,
    @SerializedName("videos") val videos: List<Video>,
    @SerializedName("websites") val websites: List<Website>,
    @SerializedName("checksum") val checksum: String,
    @SerializedName("game_localizations") val gameLocalizations: List<LanguageSupport>
)

//data class AgeRating(
//    @SerializedName("id") val id: Int
//)
//
//data class AlternativeName(
//    @SerializedName("id") val id: Int
//)

data class ExternalGame(
    @SerializedName("id") val id: Int
)

data class GameMode(
    @SerializedName("id") val id: Int
)

data class Genre(
    @SerializedName("id") val id: Int
)

data class InvolvedCompany(
    @SerializedName("id") val id: Int
)

data class Keyword(
    @SerializedName("id") val id: Int
)

data class Platform(
    @SerializedName("id") val id: Int
)

data class PlayerPerspective(
    @SerializedName("id") val id: Int
)

data class ReleaseDate(
    @SerializedName("id") val id: Int
)

data class Screenshot(
    @SerializedName("id") val id: Int
)

data class SimilarGame(
    @SerializedName("id") val id: Int
)

data class Tag(
    @SerializedName("id") val id: Int
)

data class Theme(
    @SerializedName("id") val id: Int
)

data class Video(
    @SerializedName("id") val id: Int
)

data class Website(
    @SerializedName("id") val id: Int
)

data class LanguageSupport(
    @SerializedName("id") val id: Int
)