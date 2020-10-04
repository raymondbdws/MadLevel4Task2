package com.rayray.madlevel4task2.database

import android.content.Context
import androidx.room.*
import com.rayray.madlevel4task2.dao.GameDao
import com.rayray.madlevel4task2.helper.Converters
import com.rayray.madlevel4task2.model.Game

/**
 * @author Raymond Chang
 *
 * Database
 */

@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameRoomDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao

    //Companion object = static. Only 1 instance, kan een functie zijn of variabelen
    companion object {
        private const val DATABASE_NAME = "GAMES_DATABASE"

        private var gameRoomDatabaseInstance: GameRoomDatabase? = null
        fun getDatabase(context: Context): GameRoomDatabase? {
            if (gameRoomDatabaseInstance == null) {
                gameRoomDatabaseInstance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        GameRoomDatabase::class.java,
                        DATABASE_NAME
                    ).build()
            }
            return gameRoomDatabaseInstance
        }
    }
}