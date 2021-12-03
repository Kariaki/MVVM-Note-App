package com.notenoid.notenoid.Database

import androidx.lifecycle.LiveData
import com.notenoid.myapplication.adapters.TaskEntity
import com.notenoid.myapplication.entities.NoteEntity
import com.notenoid.myapplication.entities.TaskEvent

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes:LiveData<List<NoteEntity>> = noteDao.allNotes()
    val allTask:LiveData<MutableList<TaskEntity>> = noteDao.allTask()
    suspend fun insertNote(notes: NoteEntity){
        noteDao.insert(notes)

    }
    fun allTaskEvent(currentTaskId:Long):LiveData<MutableList<TaskEvent>>{
        return noteDao.allTaskEvent(currentTaskId)
    }
    suspend fun updateNote(notes: NoteEntity){
        noteDao.updateNote(notes)

    }
    suspend fun insertTask(task: TaskEntity){
        noteDao.insertTask(task)

    }
    suspend fun deleteNote(id:Long){
        noteDao.deleteNote(id);
    }
    suspend fun deleteTask(id:Long){
        noteDao.deleteTask(id)
    }
    suspend fun deleteEvent(id:Long){
        noteDao.deleteEvent(id)
    }
    suspend fun updateTask(task: TaskEntity){
        noteDao.updateTask(task)

    }
    suspend fun insertTaskEvent(taskEvent: TaskEvent){
        noteDao.insertTaskEvent(taskEvent)

    }
    suspend fun updateTaskEvent(taskEvent: TaskEvent){
        noteDao.updateTaskEvent(taskEvent)

    }

}