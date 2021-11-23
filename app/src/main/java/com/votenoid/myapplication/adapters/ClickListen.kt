package com.votenoid.myapplication.adapters

import com.data.recyclerview_helper.SuperEntity

interface ClickListen {

    fun onClick(note: SuperEntity)
    fun onLongClick(note:SuperEntity){

    }
}