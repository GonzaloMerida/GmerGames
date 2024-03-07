package com.example.gmergames.data

import com.google.gson.annotations.SerializedName

data class Game (
    @SerializedName("name") val gameName : String,
    @SerializedName("rating") val rating : Double,
    @SerializedName("summary") val summary : String,
    @SerializedName("url") val url : String,
//    @SerializedName("genres") val genres : ,
//    @SerializedName("cover") val cover :

)
//{
//}
//data class Cover(
//
//)