package com.rayray.madlevel4task2.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rayray.madlevel4task2.model.Game

/**
 * @author Raymond Chang
 *
 * Data Access Object
 *
 * Dao must be an interface or abstract.
 *
 * Met deze interface, kun toegang krijgen tot je data.
 */
@Dao
interface GameDao {

    //Suspend Kan methodes niet aanroepen vanuit the main UI thread, freeze screen tot gevolg.

    @Query("SELECT * FROM game_table")
    suspend fun getAllGames(): List<Game>

    @Insert
    suspend fun insertGame(game: Game)

    @Query("DELETE FROM game_table")
    suspend fun deleteAllGames()
}