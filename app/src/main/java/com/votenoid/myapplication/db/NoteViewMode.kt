package com.votenoid.myapplication.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.votenoid.myapplication.Adapter.TaskEntity
import com.votenoid.myapplication.Entities.NoteEntity
import com.votenoid.myapplication.Entities.TaskEvent
import com.votenoid.votenoid.Database.NoteDatabase
import com.votenoid.votenoid.Database.NoteRepository
import com.votenoid.votenoid.Database.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

     val allNotes: LiveData<List<NoteEntity>>
     val allTask: LiveData<MutableList<TaskEntity>>
    private val repository: NoteRepository

    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        allNotes = repository.allNotes
        allTask = repository.allTask

    }
    fun allTaskEvent(taskId:Long):LiveData<MutableList<TaskEvent>>{
        return repository.allTaskEvent(taskId)

    }
    fun insertNote(note: NoteEntity){

        viewModelScope.launch (Dispatchers.IO){
            repository.insertNote(note)

        }
    }
    fun updateNote(note: NoteEntity){

        viewModelScope.launch (Dispatchers.IO){
            repository.updateNote(note)

        }
    }
    fun insertTask(task: TaskEntity){

        viewModelScope.launch (Dispatchers.IO){
            repository.insertTask(task)

        }
    }
    fun updateTask(task: TaskEntity){

        viewModelScope.launch (Dispatchers.IO){
            repository.updateTask(task)

        }
    }
    fun insertTaskEvent(task: TaskEvent){

        viewModelScope.launch (Dispatchers.IO){
            repository.insertTaskEvent(task)

        }
    }
    fun deleteNote(note:NoteEntity){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteNote(note.id)
        }
    }
    fun deleteTask(note:TaskEntity){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteTask(note.taskId)
        }
    }
    fun deleteEvent(note:TaskEvent){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteEvent(note.eventId)
        }
    }
    fun updateTaskEvent(task: TaskEvent){

        viewModelScope.launch (Dispatchers.IO){
            repository.updateTaskEvent(task)

        }
    }
}