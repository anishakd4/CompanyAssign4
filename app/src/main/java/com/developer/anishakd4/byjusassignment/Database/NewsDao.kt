package com.developer.anishakd4.byjusassignment.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.developer.anishakd4.byjusassignment.Model.ArticlesModel

@Dao
interface NewsDao{

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(night: ArticlesModel)

    @Query("SELECT * FROM News")
    fun getAllNews(): LiveData<List<ArticlesModel>>

}