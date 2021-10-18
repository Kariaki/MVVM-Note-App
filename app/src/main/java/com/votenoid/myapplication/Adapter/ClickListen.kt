package com.votenoid.myapplication.Adapter

import com.data.recyclerview_helper.SuperEntity
import com.votenoid.myapplication.Entities.NoteEntity

interface ClickListen {

    fun onClick(note: SuperEntity)
    fun onLongClick(note:SuperEntity){

    }
}