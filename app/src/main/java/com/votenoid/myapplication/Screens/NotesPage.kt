package com.votenoid.votenoid.Screens

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.data.recyclerview_helper.GeneralAdapter
import com.data.recyclerview_helper.MainViewHolder
import com.data.recyclerview_helper.SuperClickListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
import com.votenoid.myapplication.Adapter.ClickListen
import com.votenoid.myapplication.Database.NoteViewModel
import com.votenoid.myapplication.Entities.NoteEntity
import com.votenoid.myapplication.R
import com.votenoid.votenoid.Adapter.*

class NotesPage : Fragment() {

    lateinit var cardList: RecyclerView

    var adapter = GeneralAdapter()
    var noteList = listOf<NoteEntity>()
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val contentView: View = inflater.inflate(R.layout.notes, container, false)
        cardList = contentView.findViewById(R.id.cardList)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        //observing from database
        noteViewModel.allNotes.observe(viewLifecycleOwner, Observer { notes ->
            run {
                noteList = notes
                adapter.items = notes
                adapter.notifyDataSetChanged()

            }
        })


        var layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        cardList.layoutManager = layoutManager
        adapter.viewHolderPlug = viewHolderPlug
        // adapter.items = noteList
        adapter.superClickListener = clickListener
        cardList.adapter = adapter

        return contentView
    }


    var viewHolderPlug: GeneralAdapter.ViewHolderPlug = object : GeneralAdapter.ViewHolderPlug {
        override fun setPlug(group: ViewGroup, viewType: Int): MainViewHolder {
            var itemView: View =
                LayoutInflater.from(context).inflate(R.layout.note_card, group, false)
            return NoteTextViewHolder(itemView)
        }

    }
    var clickListener: SuperClickListener = object : ItemClickListener() {

        override fun onClickItem(position: Int) {

            noteclick.onClick(noteList[position])


        }

        override fun onLongClick(position: Int) {
            var dialog=dialog(noteList[position])
            dialog.setMessage("Are you sure you want to delete?")
            dialog.setTitle("Delete Note!")
            dialog.show()
        }
    }

    lateinit var noteclick: ClickListen

    fun setNoteClick(click: ClickListen) {
        noteclick = click
    }


    fun dialog(note:NoteEntity):AlertDialog{

        val alertDialog: AlertDialog? = activity?.let {
            val builder = MaterialAlertDialogBuilder(requireContext()).apply {
                setPositiveButton("Sure",
                    DialogInterface.OnClickListener { dialog, id ->
                        noteViewModel.deleteNote(note)
                    })
                setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                        dialog.dismiss()
                    })

            }
            // Set other dialog properties


            // Create the AlertDialog
            builder.create()
        }

        return alertDialog!!
    }

}