package com.example.newsapp.DataBase/*
package com.example.newsapp.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.Models.Article

@Database(
    entities = [Article::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class ArticleDatabase: RoomDatabase() {
    abstract fun getArticleDao() : ArticleDao

    companion object{
        @Volatile
        private var instance:ArticleDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context:Context) = instance?: synchronized(LOCK) {
            instance ?: createDatabase(context).also{ instance = it}
        }
        fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()
    }
}*/
