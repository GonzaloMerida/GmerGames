package com.example.gmergames.repositories

import com.example.gmergames.api.ApiService
import com.example.gmergames.data.Game
import com.example.gmergames.data.Item
import retrofit2.Response
import kotlin.random.Random

class GamesRepository(
    private val gameApiService: ApiService
) {
    suspend fun getGame(id : Int) : Response<Game>{
        return gameApiService.getGame(id)
    }

    suspend fun getItem(id : Int) : Response<Item> {
        var myItem : Item? = null
        var gameResp = gameApiService.getGame(id)
        if(gameResp.isSuccessful) {
            val game = gameResp.body()
            game?.let {
                myItem = it.toItem()
            }
            return Response.success(myItem)
        } else
            return Response.error(null,null)
    }
    suspend fun getRandomGame(): Response<Game> {
        val seed = System.currentTimeMillis()
        var x = (1..1000).random(Random(seed))
        return getGame(x)
    }
    suspend fun getRandomGames(num : Int) : Response<List<Game>>{
        var gameList : MutableList<Game> = mutableListOf()
        for (i in 1 .. num){
            val gameResp = getRandomGame()
            if(gameResp.isSuccessful){
                gameResp.body()?.let { gameList.add(gameList.size, gameResp.body()!!) }
            }
            else{
                return Response.error(null, null)
            }
        }
        return Response.success(gameList)
    }
}