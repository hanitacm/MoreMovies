package com.hanitacm.data.datasource.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    companion object {
        fun getDb(context: Context) = Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            "movies"
        ).build()
    }


    abstract val movieDao: MovieDao
}

