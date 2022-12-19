package com.example.kotlinnavigation.Models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable


@Dao
interface ArtsDao {
    @Query("SELECT * FROM Arts")
    fun getAll():Flowable<List<Arts>>

    @Insert
    fun  insert(art:Arts) : Completable

    @Delete
    fun delete(art:Arts) : Completable

}