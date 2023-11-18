package com.example.tp4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp4.database.entities.Schedule
import com.example.tp4.databinding.ActivityMainBinding
import com.example.tp4.viewmodels.BusScheduleViewModel
import com.example.tp4.viewmodels.BusScheduleViewModelFactory

class Detail : AppCompatActivity() {
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
        val busStopAdapter = BusStopAdapter(dataList) { _ -> }

        binding.scheduleView.adapter = busStopAdapter

        val stopName = intent.getStringExtra("stopName")!!
        viewModel.scheduleForStop(stopName).observe(this) {
            busStopAdapter.updateList(it)
        }

    }
}