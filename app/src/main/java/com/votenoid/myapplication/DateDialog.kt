package com.votenoid.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.votenoid.myapplication.adapters.DoneClickListener

class DateDialog() : DialogFragment() {

    lateinit var datePicker: DatePicker
    lateinit var doneButton: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val contentView =
            LayoutInflater.from(context).inflate(R.layout.date_dialog, container, false)
        doneButton = contentView.findViewById(R.id.doneButton)
        datePicker = contentView.findViewById(R.id.datePicker)
        doneButton.setOnClickListener {
            var makeString = "${datePicker.dayOfMonth} - ${datePicker.month} - ${datePicker.year}"

            doneClickListener.onDoneClick(makeString,-1)
        }
        return contentView
    }

    lateinit var doneClickListener: DoneClickListener

}