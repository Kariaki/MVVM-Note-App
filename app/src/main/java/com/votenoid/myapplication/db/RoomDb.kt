package com.votenoid.myapplication.db

import androidx.room.RoomDatabase
import com.votenoid.votenoid.Database.NoteDao
import com.votenoid.votenoid.Database.NoteDatabase

abstract class RoomDb :RoomDatabase(){

    abstract fun noteDao():NoteDao;

    companion object{

        @Volatile
        private var INSTANCE: NoteDatabase?=null
    }
}