package com.rayray.madlevel4task2.repository

import android.content.Context
import com.rayray.madlevel4task2.dao.GameDao
import com.rayray.madlevel4task2.database.GameRoomDatabase
import com.rayray.madlevel4task2.model.Game

/**
 * @author Raymond Chang
 * Repository class which is responsible for using the DAO to make operations on the database.
 * This prevents us from having to create and initialize the dao objects in the activity classes using the getDatabase method all the time.
 */
class GameRepository(context: Context) {
    private val gameDao: GameDao

    init {
        val database = GameRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    suspend fun getAllGames(): List<Game> {
        return gameDao.getAllGames()
    }

    suspend fun insertGame(game: Game) {
        return gameDao.insertGame(game)
    }

    suspend fun deleteAllGames() {
        gameDao.deleteAllGames()
    }

}