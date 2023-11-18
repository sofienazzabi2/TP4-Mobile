package com.example.tp4.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tp4.database.entities.Schedule
import com.example.tp4.database.entities.ScheduleDao

class BusScheduleViewModel(private val scheduleDao: ScheduleDao): ViewModel() {
    fun fullSchedule(): LiveData<List<Schedule>> = scheduleDao.getAll()
    fun scheduleForStop(stopName: String): LiveData<List<Schedule>> = scheduleDao.getByStopName(stopName)
}