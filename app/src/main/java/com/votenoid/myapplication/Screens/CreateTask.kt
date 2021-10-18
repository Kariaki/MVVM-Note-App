package com.votenoid.myapplication.Screens

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.data.recyclerview_helper.GeneralAdapter
import com.data.recyclerview_helper.MainViewHolder
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.votenoid.myapplication.Adapter.DoneClickListener
import com.votenoid.myapplication.Adapter.TaskEventViewHolder
import com.votenoid.myapplication.Adapter.TaskEntity
import com.votenoid.myapplication.Database.NoteViewModel
import com.votenoid.myapplication.DateDialog
import com.votenoid.myapplication.Entities.NoteEntity
import com.votenoid.myapplication.Entities.TaskEvent
import com.votenoid.myapplication.R
import com.votenoid.myapplication.TaskDialog
import com.votenoid.votenoid.Adapter.ItemClickListener

class CreateTask(var currentTask: TaskEntity?) : Fragment() {


    lateinit var taskList: RecyclerView
    var generalAdapter = GeneralAdapter()
    var taskEventtems = mutableListOf<TaskEvent>()
    lateinit var addDueDate: LinearLayout
    lateinit var deadline: TextView
    lateinit var done: ImageButton
    lateinit var taskDescription: EditText
    lateinit var back: ImageView
    lateinit var noteViewModel: NoteViewModel

    var taskId = System.currentTimeMillis()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val contentView: View = inflater.inflate(R.layout.create_task, container, false)
        taskList = contentView.findViewById(R.id.taskList)
        addDueDate = contentView.findViewById(R.id.addDueDate)
        done = contentView.findViewById(R.id.done)
        taskDescription = contentView.findViewById(R.id.taskDescription)
        deadline = contentView.findViewById(R.id.deadline)
        back = contentView.findViewById(R.id.back)


        setDetails()

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        generalAdapter.apply {
            this.viewHolderPlug = viewPlug
            superClickListener = itemClick


        }
        taskList.apply {
            taskList.layoutManager = LinearLayoutManager(context)
            taskList.hasFixedSize()
            taskList.adapter = generalAdapter

        }
        addDueDate.setOnClickListener {
            val dateDialog = DateDialog()
            dateDialog.doneClickListener = object : DoneClickListener {
                override fun onDoneClick(date: String, priority: Int) {
                    deadline.text = date
                    dateDialog.dismiss()
                }
            }
            dateDialog.show(childFragmentManager, null)
        }

        back.setOnClickListener {
            activity?.onBackPressed()
        }
        noteViewModel.allTaskEvent(taskId).observe(viewLifecycleOwner, {
            generalAdapter.items = it
            taskEventtems = it
            generalAdapter.notifyDataSetChanged()
        })

        insertTask()
        //adding task to database

        val addTaskFab: Button = contentView.findViewById(R.id.addTask)
        addTaskFab.setOnClickListener {
            var taskDialog = TaskDialog()
            taskDialog.doneClickListener = object : DoneClickListener {
                override fun onDoneClick(taskEventDescription: String, priority: Int) {
                    insertTaskEvent(taskEventDescription, priority)
                    taskDialog.dismiss()
                }
            }
            taskDialog.show(childFragmentManager, null)
        }


        return contentView
    }

    private fun setDetails() {
        if (currentTask != null) {
            taskDescription.setText(currentTask!!.taskTittle)
            taskId = currentTask!!.taskId
            deadline.text = (currentTask!!.dueDate)
        }
    }

    //adding task to database
    fun insertTask() {
        done.setOnClickListener {
            var description = taskDescription.text.toString()

            if (currentTask == null) {
                if (description.isNotEmpty()) {
                    var newTask = TaskEntity(
                        taskId,
                        description,
                        deadline.text.toString(),
                        0, 0
                    )
                    noteViewModel.insertTask(newTask)
                    Toast.makeText(context, "task added", Toast.LENGTH_LONG).show()
                    currentTask = newTask
               }

            } else {
                if (description.isNotEmpty()) {
                    var newTask = TaskEntity(
                        currentTask!!.taskId,
                        description,
                        deadline.text.toString(),
                        0, 0
                    )
                    noteViewModel.updateTask(newTask)
                    currentTask = newTask
                }
            }
        }

    }

    //adding task event to db
    fun insertTaskEvent(description: String, priority: Int) {

        val newTaskEvent = TaskEvent(
            System.currentTimeMillis(),
            taskId,
            description,
            priority,
            false
        )

        noteViewModel.insertTaskEvent(newTaskEvent)

    }

    override fun onDetach() {
        super.onDetach()
       if(currentTask!=null) {
           currentTask?.taskCount=taskEventtems.size
           noteViewModel.updateTask(currentTask!!)
       }

    }

    private val viewPlug = object : GeneralAdapter.ViewHolderPlug {
        override fun setPlug(group: ViewGroup, viewType: Int): MainViewHolder {

            var viewPlug = LayoutInflater.from(context).inflate(R.layout.task_event, group, false)
            return TaskEventViewHolder(viewPlug)
        }
    }
    private val itemClick = object : ItemClickListener() {
        override fun onClickItem(position: Int) {

        }

        override fun onClickEvent(position: Int, completed: Boolean) {

            var updateEvent = taskEventtems[position]
            updateEvent.completed = completed

            noteViewModel.updateTaskEvent(updateEvent)
            if (completed) {
                currentTask?.completed = currentTask?.completed!! + 1
            } else {

                currentTask?.completed = currentTask?.completed!! - 1
            }
            noteViewModel.updateTask(currentTask!!)
        }

        override fun onLongClick(position: Int) {
            val dialog = dialog(taskEventtems[position])
            dialog.setMessage("Are you sure you want to delete this event?")
            dialog.show()
        }
    }

    fun dialog(note: TaskEvent): AlertDialog {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = MaterialAlertDialogBuilder(requireContext()).apply {
                setPositiveButton("Sure",
                    DialogInterface.OnClickListener { dialog, id ->
                        noteViewModel.deleteEvent(note)
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