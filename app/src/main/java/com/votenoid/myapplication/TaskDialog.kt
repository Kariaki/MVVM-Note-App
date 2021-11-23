package com.votenoid.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.votenoid.myapplication.adapters.DoneClickListener

class TaskDialog : DialogFragment() {

    lateinit var description: EditText
    lateinit var priority: EditText
    lateinit var addTask: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contentView =
            LayoutInflater.from(context).inflate(R.layout.task_dialog, container, false)

        addTask = contentView.findViewById(R.id.addTask)
        description = contentView.findViewById(R.id.description)
        priority = contentView.findViewById(R.id.priority)


        addTask.setOnClickListener {
            var eventPriority = if (priority.text.toString().isNotEmpty())
                priority.text.toString().toInt()
            else
                1

            doneClickListener.onDoneClick(description.text.toString(), priority = eventPriority)
        }
        return contentView
    }

    lateinit var doneClickListener: DoneClickListener


}