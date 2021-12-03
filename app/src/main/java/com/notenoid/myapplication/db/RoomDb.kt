package com.notenoid.myapplication.db

import androidx.room.RoomDatabase
import com.notenoid.notenoid.Database.NoteDao
import com.notenoid.notenoid.Database.NoteDatabase

abstract class RoomDb :RoomDatabase(){

    abstract fun noteDao():NoteDao;

    companion object{

        @Volatile
        private var INSTANCE: NoteDatabase?=null
    }
}