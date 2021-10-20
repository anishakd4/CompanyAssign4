package com.developer.anishakd4.byjusassignment.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.developer.anishakd4.byjusassignment.Model.ArticlesModel

@Database(entities = [ArticlesModel::class], version = 1, exportSchema = false)
abstract class NewsDatabase: RoomDatabase(){

    abstract val newsDao: NewsDao

    companion object{

        @Volatile
        private var INSTANCE: NewsDatabase? = null

        fun getInstance(context: Context):NewsDatabase {
            var instance = INSTANCE

            if(instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "news_database").fallbackToDestructiveMigration().build()

                INSTANCE = instance
            }

            return instance
        }
    }

}