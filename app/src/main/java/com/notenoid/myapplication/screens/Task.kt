package com.notenoid.notenoid.Screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.data.recyclerview_helper.GeneralAdapter
import com.data.recyclerview_helper.MainViewHolder
import com.notenoid.myapplication.adapters.ClickListen
import com.notenoid.myapplication.adapters.TaskEntity
import com.notenoid.myapplication.adapters.TaskViewHolder
import com.notenoid.myapplication.db.NoteViewModel
import com.notenoid.myapplication.R
import com.notenoid.myapplication.screens.DialogHelper
import com.notenoid.notenoid.Adapter.ItemClickListener


class Task : Fragment() {

    lateinit var taskList: RecyclerView
    var generalAdapter = GeneralAdapter()
    var taskItems = mutableListOf<TaskEntity>()

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val contentView: View = inflater.inflate(R.layout.task, container, false)
        taskList = contentView.findViewById(R.id.taskList)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        //populate()

        noteViewModel.taskClickListen.observe(viewLifecycleOwner) {
            taskClick = it
        }

        generalAdapter.apply {
            this.viewHolderPlug = viewPlug
            superClickListener = itemClick


        }
        noteViewModel.allTask.observe(viewLifecycleOwner, Observer {

            taskItems = it
            generalAdapter.items = it
            generalAdapter.notifyDataSetChanged()
        })

        taskList.apply {
            taskList.layoutManager = LinearLayoutManager(context)
            taskList.hasFixedSize()
            taskList.adapter = generalAdapter

        }



        return contentView
    }

    val viewPlug = object : GeneralAdapter.ViewHolderPlug {
        override fun setPlug(group: ViewGroup, viewType: Int): MainViewHolder {

            var viewPlug = LayoutInflater.from(context).inflate(R.layout.task_card, group, false)
            return TaskViewHolder(viewPlug)
        }
    }
    val itemClick = object : ItemClickListener() {
        override fun onClickItem(position: Int) {
            //  if (taskClick != null)

            taskClick?.onClick(taskItems[position])
        }

        override fun onLongClick(position: Int) {
            //taskClick.onLongClick(taskItems[position])
            val dialog = DialogHelper.dialog(requireContext(),requireActivity()){
                noteViewModel.deleteTask(taskItems[position])
            }
            dialog.setTitle("Delete Task!")
            dialog.setMessage("Are you sure you want to delete?")
            dialog.show()
        }

        override fun callOnViewHolder(position: Int, message: TextView) {
            val item = taskItems[position]

            noteViewModel.allTaskEvent(item.taskId)
                .observe(viewLifecycleOwner, {
                    val text = "${it.size} task"
                    message.text = text
                })
        }
    }

    var taskClick: ClickListen? = null


}