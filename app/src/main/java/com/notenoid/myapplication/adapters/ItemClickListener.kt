package com.notenoid.notenoid.Adapter

import android.widget.TextView
import com.data.recyclerview_helper.SuperClickListener

open class ItemClickListener : SuperClickListener {

    override fun onClickItem(position: Int) {

    }

    open fun onClickEvent(position: Int, completed: Boolean) {

    }

    open fun onLongClick(position: Int){

    }
    open fun callOnViewHolder(position: Int,message:TextView){

    }
}