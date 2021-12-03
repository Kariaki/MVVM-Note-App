package com.notenoid.myapplication.adapters

import android.content.Context
import android.view.View
import android.widget.TextView
import com.data.recyclerview_helper.MainViewHolder
import com.data.recyclerview_helper.SuperClickListener
import com.data.recyclerview_helper.SuperEntity
import com.notenoid.myapplication.R
import com.notenoid.notenoid.Adapter.ItemClickListener

class TaskViewHolder(itemView: View) : MainViewHolder(itemView = itemView) {
    lateinit var tittle: TextView
    lateinit var description: TextView
    override fun bindPostType(
        types: SuperEntity,
        context: Context,
        clickListener: SuperClickListener
    ) {

        clickListener as ItemClickListener
        tittle = itemView.findViewById(R.id.tittle)
        description = itemView.findViewById(R.id.body)
        types as TaskEntity
        tittle.text = types.taskTittle

        clickListener.callOnViewHolder(adapterPosition,description)

        itemView.setOnClickListener {
            clickListener.onClickItem(adapterPosition)
        }
        itemView.setOnLongClickListener(
            object : View.OnLongClickListener {
                override fun onLongClick(p0: View?): Boolean {
                    clickListener.onLongClick(adapterPosition)
                    return true
                }
            })
    }

}