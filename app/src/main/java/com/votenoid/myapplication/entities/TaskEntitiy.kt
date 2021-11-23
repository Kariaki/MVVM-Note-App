package com.votenoid.myapplication.adapters

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.data.recyclerview_helper.SuperEntity

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey
    val taskId:Long,
    var taskTittle:String,
    var dueDate:String,
    var taskCount:Int,
    var completed:Int
): SuperEntity()
