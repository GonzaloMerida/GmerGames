package com.example.gmergames.repositories

import com.example.gmergames.api.ApiService
import com.example.gmergames.data.Game
import com.example.gmergames.data.Item
import com.example.gmergames.datamodel.GameDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import kotlin.random.Random

class GamesRepository(
    private val gameApiService: ApiService,
    private val ioDispatcher : CoroutineDispatcher = Dispatchers.IO,
    private val gameDAO : GameDao
) {
    suspend fun getGames() : Response<ArrayList<Game>>{
        return gameApiService.getGames(queryGetGames())
    }

    suspend fun getGame()

    suspend fun getGamesByName(name : String) : Response<ArrayList<Game>>{
        return gameApiService.getGamesByName(name, queryGetGamesByName())
    }

    suspend fun addItemToFav(item : Item) = withContext(ioDispatcher){
        gameDAO.insertGame(item)
    }

//    suspend fun getFavGames() = withContext(ioDispatcher){
//
//    }

    suspend fun getItem(id : Int) : Response<Item> {
        var myItem : Item? = null
        var gameResp = gameApiService.getGames(queryGetGames())
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
        return getGames(queryGetGames())
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

    companion object{

        const val MAX_GAMES = 20
        const val MAX_GAMES_BY_NAME = 5
        fun queryGetGames() : String{
            return "id, name, summary, genres.name, screenshots.url, platforms.name; limit $MAX_GAMES;"
        }
        fun queryGetGamesByName() : String{
            return "id, name, summary, genres.name, screenshots.url, platforms.name; limit $MAX_GAMES_BY_NAME;"
        }

    }

}