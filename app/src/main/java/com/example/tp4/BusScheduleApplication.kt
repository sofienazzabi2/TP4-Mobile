package com.example.tp4

import android.app.Application
import com.example.tp4.database.AppDatabase

class BusScheduleApplication: Application() {
    val db: AppDatabase by lazy { AppDatabase.getDb(this) }
}