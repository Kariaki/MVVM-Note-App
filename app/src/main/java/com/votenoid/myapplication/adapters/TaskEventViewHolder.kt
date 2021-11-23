package com.votenoid.myapplication.adapters

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import com.data.recyclerview_helper.MainViewHolder
import com.data.recyclerview_helper.SuperClickListener
import com.data.recyclerview_helper.SuperEntity
import com.votenoid.myapplication.entities.TaskEvent
import com.votenoid.myapplication.R
import com.votenoid.votenoid.Adapter.ItemClickListener

class TaskEventViewHolder(view: View) : MainViewHolder(view) {

    lateinit var tittle: TextView
    lateinit var completed: AppCompatCheckBox

    override fun bindPostType(
        event: SuperEntity,
        context: Context,
        clickListener: SuperClickListener
    ) {
        tittle = itemView.findViewById(R.id.tittle)
        completed = itemView.findViewById(R.id.completed)

        event as TaskEvent
        tittle.text = event.eventDescription
        completed.isChecked = event.completed

        clickListener as ItemClickListener
        completed.setOnCheckedChangeListener { compoundButton, b ->
            clickListener.onClickEvent(adapterPosition,b)
        }


        itemView.setOnLongClickListener (
            object : View.OnLongClickListener {
                override fun onLongClick(p0: View?): Boolean {
                    clickListener.onLongClick(adapterPosition)
                    return true
                }
            } )


        itemView.setOnClickListener {
            completed.toggle()
        }
    }
}