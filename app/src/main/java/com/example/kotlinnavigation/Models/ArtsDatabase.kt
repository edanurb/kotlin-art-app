package com.example.kotlinnavigation.Models

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Arts::class], version =4 )
abstract class ArtsDatabase : RoomDatabase() {
    abstract fun artsDao(): ArtsDao
}
