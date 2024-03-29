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
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val gameDAO: GameDao
) {
    suspend fun getGames(): Response<List<Game>> {
        return gameApiService.getGames(queryGetGames())
    }

    suspend fun getGame(): Response<Game> {
        return gameApiService.getGame(queryGetRandomGame())
    }

    suspend fun getGamesByName(name: String): Response<List<Game>> {
        return gameApiService.getGamesByName(name, queryGetGamesByName())
    }

    suspend fun getGameById(id: Int): Response<Game> {
        return gameApiService.getGameById(id, queryGetGameById(id))
    }

    suspend fun addItemToFav(item: Item) = withContext(ioDispatcher) {
        gameDAO.insertGame(item)
    }

    suspend fun getFavGames() = withContext(ioDispatcher) {
        gameDAO.getAllGames()
    }

    suspend fun deleteFavGame(item: Item) = withContext(ioDispatcher) {
        gameDAO.deleteGame(item)
    }

    suspend fun getItem(id: Int): Response<Item> {
        var myItem: Item? = null
        var gameResp = gameApiService.getGame(queryGetGameById(id))
        if (gameResp.isSuccessful) {
            val game = gameResp.body()
            game?.let {
                myItem = it.toItem()
            }
            return Response.success(myItem)
        } else
            return Response.error(null, null)
    }

    suspend fun getRandomGames(num: Int): Response<List<Item>> {
        var myItem: Item? = null
        var gameList: MutableList<Item> = mutableListOf()
        for (i in 1..num) {
            var gameResp = getGame()
            if (gameResp.isSuccessful) {
                var game = gameResp.body()
                game?.let {
                    myItem = it.toItem()
                    gameList.add(gameList.size, myItem!!)
                }
            } else {
                return Response.error(null, null)
            }
        }
        return Response.success(gameList)
    }

    companion object {

        const val RANDOM_GAME = 1
        const val MAX_GAMES = 20
        const val MAX_GAMES_BY_NAME = 5
        fun queryGetGames(): String {
            return "id, name, summary, genres.name, screenshots.url; limit $MAX_GAMES;"
        }

        fun queryGetGamesByName(): String {
            return "id, name, summary, genres.name, screenshots.url; limit $MAX_GAMES_BY_NAME;"
        }

        fun queryGetRandomGame(): String {
            return "id, name, summary, genres.name, screenshots.url; limit $RANDOM_GAME;"
        }

        fun queryGetGameById(idAsked: Int): String {
            return "id, name, summary, genres.name, screenshots.url; where id = $idAsked"
        }

        fun queryGetGenres(): String {
            return "name;"
        }

    }

}