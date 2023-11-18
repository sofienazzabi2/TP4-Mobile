package com.example.tp4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp4.database.entities.Schedule
import com.example.tp4.databinding.ActivityMainBinding
import com.example.tp4.viewmodels.BusScheduleViewModel
import com.example.tp4.viewmodels.BusScheduleViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: BusScheduleViewModel by viewModels() {
        BusScheduleViewModelFactory((application as BusScheduleApplication).db.scheduleDae())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.scheduleView.layoutManager = LinearLayoutManager(this)
        binding.scheduleView.hasFixedSize()

        var dataList = listOf<Schedule>()
        val busStopAdapter = BusStopAdapter(dataList) { schedule ->
            val intent = Intent(this, Detail::class.java)
            intent.putExtra("stopName", schedule.stopName)
            startActivity(intent)
        }
        binding.scheduleView.adapter = busStopAdapter

        viewModel.fullSchedule().observe(this) {
            busStopAdapter.updateList(it)
        }
    }
}