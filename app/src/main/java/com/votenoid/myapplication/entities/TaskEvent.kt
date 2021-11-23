package com.votenoid.myapplication.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.data.recyclerview_helper.SuperEntity

@Entity(tableName = "task_event")
data class TaskEvent(
    @PrimaryKey val eventId: Long,
    val taskId: Long,
    var eventDescription: String,
    var priority: Int,
    var completed: Boolean
) : SuperEntity()
