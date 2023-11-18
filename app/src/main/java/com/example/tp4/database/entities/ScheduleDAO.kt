package com.example.tp4.database.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao()
interface ScheduleDao {
    @Query("select * from schedule order by arrival_time")
    fun getAll(): LiveData<List<Schedule>>

    @Query("select * from schedule where stop_name=:stopName")
    fun getByStopName(stopName: String): LiveData<List<Schedule>>
}