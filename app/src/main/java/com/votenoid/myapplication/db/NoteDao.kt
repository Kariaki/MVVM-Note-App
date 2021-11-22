package com.votenoid.votenoid.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.votenoid.myapplication.Adapter.TaskEntity
import com.votenoid.myapplication.Entities.NoteEntity
import com.votenoid.myapplication.Entities.TaskEvent

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity)
    @Query("delete  from note where id like:noteId")
    suspend fun deleteNote(noteId:Long)

    @Query("delete  from task where taskId like:taskId")
    suspend fun deleteTask(taskId:Long)

    @Query("delete  from task_event where eventId like:eventId")
    suspend fun deleteEvent(eventId:Long)

    @Query("select * from note")
    fun allNotes(): LiveData<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskEvent(event: TaskEvent)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Update
    suspend fun updateTaskEvent(taskEvent: TaskEvent)


    @Query("select * from task_event where taskId like:currentTaskId order by priority desc ")
    fun allTaskEvent(currentTaskId: Long): LiveData<MutableList<TaskEvent>>



    @Query("select * from task order by taskId desc ")
    fun allTask(): LiveData<MutableList<TaskEntity>>


}