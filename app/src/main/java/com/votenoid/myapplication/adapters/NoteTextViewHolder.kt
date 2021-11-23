package com.votenoid.votenoid.Adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import com.data.recyclerview_helper.MainViewHolder
import com.data.recyclerview_helper.SuperClickListener
import com.data.recyclerview_helper.SuperEntity
import com.votenoid.myapplication.entities.NoteEntity
import com.votenoid.myapplication.R

class NoteTextViewHolder(itemView: View) : MainViewHolder(itemView) {

    lateinit var body: TextView
    lateinit var tittle: TextView
    override fun bindPostType(
        note: SuperEntity,
        context: Context,
        clickListener: SuperClickListener
    ) {
        note as NoteEntity

        clickListener as ItemClickListener
        body = itemView.findViewById(R.id.body)
        tittle = itemView.findViewById(R.id.tittle)

        body.text = note.body
        tittle.text = note.tittle
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