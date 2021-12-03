package com.notenoid.notenoid.Screens

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.data.recyclerview_helper.GeneralAdapter
import com.data.recyclerview_helper.MainViewHolder
import com.data.recyclerview_helper.SuperClickListener
import com.notenoid.myapplication.adapters.ClickListen
import com.notenoid.myapplication.db.NoteViewModel
import com.notenoid.myapplication.entities.NoteEntity
import com.notenoid.myapplication.R
import com.notenoid.myapplication.screens.DialogHelper
import com.notenoid.notenoid.Adapter.*

class NotesPage : Fragment() {

    lateinit var cardList: RecyclerView
    var adapter = GeneralAdapter()
    var noteList = listOf<NoteEntity>()
    private lateinit var noteViewModel: NoteViewModel

    lateinit var noteclick: ClickListen

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val contentView: View = inflater.inflate(R.layout.notes, container, false)
        cardList = contentView.findViewById(R.id.cardList)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        //observing from database
        noteViewModel.allNotes.observe(viewLifecycleOwner, { notes ->
            run {
                noteList = notes
                adapter.items = notes
                adapter.notifyDataSetChanged()

            }
        })


        val layoutManager =
            if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT)
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            else
                GridLayoutManager(requireContext(), 3)

        cardList.layoutManager = layoutManager
        adapter.viewHolderPlug = viewHolderPlug
        // adapter.items = noteList
        adapter.superClickListener = clickListener
        cardList.adapter = adapter

        return contentView
    }


    var viewHolderPlug: GeneralAdapter.ViewHolderPlug = object : GeneralAdapter.ViewHolderPlug {
        override fun setPlug(group: ViewGroup, viewType: Int): MainViewHolder {
            val itemView: View =
                LayoutInflater.from(context).inflate(R.layout.note_card, group, false)
            return NoteTextViewHolder(itemView)
        }

    }
    var clickListener: SuperClickListener = object : ItemClickListener() {

        override fun onClickItem(position: Int) {

            noteclick.onClick(noteList[position])

        }

        override fun onLongClick(position: Int) {
            val dialog = DialogHelper.dialog(requireContext(),requireActivity()){
                noteViewModel.deleteNote(noteList[position])
            }
            dialog.setMessage("Are you sure you want to delete?")
            dialog.setTitle("Delete Note!")
            dialog.show()
        }
    }



}