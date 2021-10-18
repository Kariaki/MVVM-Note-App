package com.votenoid.votenoid.Screens

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.data.recyclerview_helper.SuperEntity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.votenoid.myapplication.Adapter.ClickListen
import com.votenoid.myapplication.Adapter.TaskEntity
import com.votenoid.myapplication.Entities.NoteEntity
import com.votenoid.myapplication.R
import com.votenoid.myapplication.Screens.CreateTask
import com.votenoid.votenoid.Adapter.ItemClickListener
import com.votenoid.votenoid.Adapter.PageAdapter


class Launch : AppCompatActivity() {

    lateinit var home_tab: TabLayout
    var search: EditText? = null
    var settings: ImageButton? = null
    lateinit var pages: ViewPager2
    var pageAdapter: PageAdapter? = null
    var pageList = ArrayList<Fragment>()

    var context: Context? = null

    lateinit var addButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launch)

        context = this
        home_tab = findViewById(R.id.home_tab)
        search = findViewById(R.id.search)
        pages = findViewById(R.id.viewpager)
        settings = findViewById(R.id.settings)
        addButton = findViewById(R.id.add)
        pageAdapter = PageAdapter(this)
        var notePage = NotesPage()


        notePage.setNoteClick(noteClickListen)

        pageList.add(notePage)
        var taskPage=Task()
        taskPage.setNoteClick(taskClickListen)
        pageList.add(taskPage)
        pageAdapter?.fragmentList = pageList




        pages.isUserInputEnabled = true

        pages.adapter = pageAdapter
        TabLayoutMediator(home_tab, pages) { tab, position ->
            run {

                when (position) {
                    0 ->
                        tab.text = "Notes"
                    1 ->
                        tab.text = "Tasks"
                }
            }
        }.attach()

        pages.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {

            }
        })

    }

    fun addNote(view: View) {


        var manager = supportFragmentManager
        var transaction = manager.beginTransaction()
        when (pages.currentItem) {
            0 ->

                transaction.replace(R.id.root, WriteNotes(null)).addToBackStack(null).commit()

            1 ->
                transaction.replace(R.id.root, CreateTask(null)).addToBackStack(null).commit()
        }

        addButton.visibility = View.GONE
    }

    fun openNote(note: NoteEntity) {
        var manager = supportFragmentManager
        var transaction = manager.beginTransaction()
        transaction.replace(R.id.root, WriteNotes(note)).addToBackStack(null).commit()
        addButton.visibility = View.GONE

    }
    fun openTask(task:TaskEntity?){

        var manager = supportFragmentManager
        var transaction = manager.beginTransaction()
        transaction.replace(R.id.root, CreateTask(task)).addToBackStack(null).commit()
        addButton.visibility = View.GONE
    }

    fun settingsPage(view: View) {


    }

    //implement onlong click
    val noteClickListen=object : ClickListen {
        override fun onClick(note: SuperEntity) {
            note as NoteEntity
            openNote(note)
        }

        override fun onLongClick(note: SuperEntity) {

        }
    }

    //implement onlongclick
    val taskClickListen=object : ClickListen {
        override fun onClick(task: SuperEntity) {
            task as TaskEntity
            openTask(task)
        }

        override fun onLongClick(note: SuperEntity) {
            super.onLongClick(note)
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        addButton.visibility = View.VISIBLE
    }
}