package com.example.tp4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tp4.database.entities.Schedule
import com.example.tp4.database.entities.ScheduleDao

@Database(entities = [Schedule::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun scheduleDae(): ScheduleDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDb(context: Context): AppDatabase {
            if(instance != null) return instance!!

            instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "bus_schedule"
            ).createFromAsset("database/bus_schedule.db")
                //.allowMainThreadQueries()
                .build()
            return instance!!
        }
    }
}