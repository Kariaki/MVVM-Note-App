package com.notenoid.notenoid.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val tittle:String,
    val body:String,
    val type:Int,

)
