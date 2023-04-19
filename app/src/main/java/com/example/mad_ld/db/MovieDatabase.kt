package com.example.mad_ld.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mad_ld.db.dao.MovieDao
import com.example.mad_ld.db.entity.Movie
import com.example.mad_ld.utils.CustomConverters

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CustomConverters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var Instance: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MovieDatabase::class.java, "movie_db")
                    .createFromAsset("movies.db")
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build()
                    .also { Instance = it }
            }
        }
    }
}