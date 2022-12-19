package com.example.kotlinnavigation.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Arts(

    @ColumnInfo(name="name")
    var name:String,

    @ColumnInfo(name="description")
    var description:String,

    @ColumnInfo(name="date")
    var date:String,
    @PrimaryKey (autoGenerate = true)
    var id: Int =0

) {
}