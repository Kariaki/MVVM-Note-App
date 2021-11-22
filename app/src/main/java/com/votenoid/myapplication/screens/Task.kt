package com.votenoid.votenoid.Screens

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.data.recyclerview_helper.GeneralAdapter
import com.data.recyclerview_helper.MainViewHolder
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.votenoid.myapplication.Adapter.ClickListen
import com.votenoid.myapplication.Adapter.TaskEntity
import com.votenoid.myapplication.Adapter.TaskViewHolder
import com.votenoid.myapplication.Database.NoteViewModel
import com.votenoid.myapplication.R
import com.votenoid.votenoid.Adapter.ItemClickListener


class Task : Fragment() {

    lateinit var taskList:RecyclerView
    var generalAdapter=GeneralAdapter()
    var taskItems = mutableListOf<TaskEntity>()

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val contentView:View= inflater.inflate(R.layout.task, container, false)
        taskList=contentView.findViewById(R.id.taskList)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        //populate()


        generalAdapter.apply {
            this.viewHolderPlug=viewPlug
            superClickListener=itemClick


        }
        noteViewModel.allTask.observe(viewLifecycleOwner, Observer {

            taskItems=it
            generalAdapter.items=it
            generalAdapter.notifyDataSetChanged()
        })

        taskList.apply {
            taskList.layoutManager=LinearLayoutManager(context)
            taskList.hasFixedSize()
            taskList.adapter=generalAdapter

        }





        return contentView
    }

    val viewPlug= object : GeneralAdapter.ViewHolderPlug {
        override fun setPlug(group: ViewGroup, viewType: Int): MainViewHolder {

            var viewPlug=LayoutInflater.from(context).inflate(R.layout.task_card,group,false)
            return TaskViewHolder(viewPlug)
        }
    }
    val itemClick= object : ItemClickListener() {
        override fun onClickItem(position: Int) {
            noteclick.onClick(taskItems[position])

        }

        override fun onLongClick(position: Int) {
            //noteclick.onLongClick(taskItems[position])
            val dialog=dialog(taskItems[position])
            dialog.setTitle("Delete Task!")
            dialog.setMessage("Are you sure you want to delete?")
            dialog.show()
        }

        override fun callOnViewHolder(position: Int, message: TextView) {
            val item=taskItems[position]

            noteViewModel.allTaskEvent(item.taskId)
                .observe(viewLifecycleOwner,{
                    val text="${it.size} task"
                    message.text=text
                })
        }
    }

    lateinit var noteclick: ClickListen

    fun setNoteClick(click: ClickListen) {
        noteclick = click
    }

    fun dialog(task:TaskEntity): AlertDialog {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = MaterialAlertDialogBuilder(requireContext()).apply {
                setPositiveButton("Sure",
                    DialogInterface.OnClickListener { dialog, id ->
                        noteViewModel.deleteTask(task)
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