package com.sample.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sample.newsapp.models.ArticlesItem
import java.security.AccessControlContext


@Database(
        entities = [ArticlesItem::class],
        version = 1)
@TypeConverters(Converters::class)
abstract class NewsDatabase :RoomDatabase() {

    abstract fun getNewsDao(): NewsDao

    companion object{
        @Volatile
        private var instance : NewsDatabase? = null
        private val LOCK = Any()
        operator fun invoke (context: Context) = instance ?: synchronized(LOCK){
            instance?: createDatabase(context).also{ instance = it}
        }

        private fun createDatabase(context: Context)=
                Room.databaseBuilder(context.applicationContext,
                        NewsDatabase::class.java,
                        "newsDatabase.db").build()
    }

}