package com.notenoid.myapplication.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.notenoid.myapplication.db.NoteViewModel
import com.notenoid.myapplication.R

class BlankFragment : Fragment() {



    lateinit var note_text: EditText
    lateinit var done: ImageButton
    var undo: ImageButton? = null
    var textState = ArrayList<String>()
    lateinit var redo: ImageButton
    lateinit var noteViewModel: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var contentView= inflater.inflate(R.layout.fragment_blank, container, false)


        note_text = contentView.findViewById(R.id.note_text)
        done = contentView.findViewById(R.id.done)
        redo = contentView.findViewById(R.id.redo)
        undo = contentView.findViewById(R.id.undo)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        done.setOnClickListener { Toast.makeText(context, "hello hello", Toast.LENGTH_LONG) }


        //   clickListeners()


        textWatcher()

        return contentView
    }

    companion object{
        interface listner{
            fun clickMe()

        }
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
                textState.add(s.toString())

            }
        })
    }

}