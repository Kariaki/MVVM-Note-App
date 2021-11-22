package com.votenoid.votenoid.Screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.votenoid.myapplication.Database.NoteViewModel
import com.votenoid.myapplication.Entities.NoteEntity
import com.votenoid.myapplication.NoteTypes
import com.votenoid.myapplication.R

class WriteNotes(var currentNote: NoteEntity?) : Fragment() {


    lateinit var note_text: EditText
    lateinit var done: ImageButton
    var undo: ImageButton? = null
    lateinit var wrapper: RelativeLayout
    var textState = ArrayList<String>()
    lateinit var redo: ImageButton
    lateinit var noteViewModel: NoteViewModel
    lateinit var charCount: TextView
    lateinit var noteTittle: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val contentView: View = inflater.inflate(R.layout.write_notes, container, false)

        note_text = contentView.findViewById(R.id.note_text)
        done = contentView.findViewById(R.id.done)
        redo = contentView.findViewById(R.id.redo)
        noteTittle = contentView.findViewById(R.id.note_tittle)
        charCount = contentView.findViewById(R.id.charCount)
        undo = contentView.findViewById(R.id.undo)
        wrapper = contentView.findViewById(R.id.wrapper)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        done.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                doneAction()
            }

        })

        clickListeners()


        textWatcher()

        setNotes()

        return contentView
    }

    fun textWatcher() {
        note_text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                if (!s.toString().isEmpty()) {
                    done.background = resources.getDrawable(R.drawable.done_color)

                } else {

                    done.background = resources.getDrawable(R.drawable.done)
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var str = s.toString()
                var spaceCount = str.count { it == ' ' }

                charCount.text = if(spaceCount>0)
                    spaceCount.toString()
                else
                    "1"
                charCount.append(" words")
                textState.add(s.toString())

            }
        })
    }

    fun clickListeners() {


        redo.setOnClickListener {
            redoAction()
        }
        undo?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                undoAction()
            }

        })

    }

    fun doneAction() {

        if (!note_text.text.toString().isEmpty()) {
            var note = NoteEntity(
                System.currentTimeMillis(),
                NoteTypes.textNote,
                System.currentTimeMillis(),
                noteTittle.text.toString(),
                note_text.text.toString()
            )

            if (currentNote == null) {

                noteViewModel.insertNote(note)
                Toast.makeText(context, "saved", Toast.LENGTH_LONG).show()
            } else {

                note.id = currentNote!!.id
                noteViewModel.updateNote(note)

            }
        }
    }


    fun redoAction() {

        var currentState = note_text.text.toString()
        var index = textState.indexOf(currentState)
        index++

        var rollback = if (index < textState.size - 1)
            index
        else
            textState.size - 1
        note_text.setText(textState[rollback])

    }

    fun undoAction() {

        var currentState = note_text.text.toString()
        var index = textState.indexOf(currentState)
        index--

        var rollback = if (index >= 0)
            index
        else
            0
        note_text.setText(textState.get(rollback))

    }

    fun setNotes() {
        if (currentNote != null) {
            noteTittle.setText(currentNote?.tittle)
            note_text.setText(currentNote?.body)
            charCount.text = (currentNote?.body?.length).toString()
        }
    }


}