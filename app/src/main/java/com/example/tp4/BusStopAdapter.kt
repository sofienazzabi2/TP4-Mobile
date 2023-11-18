package com.example.tp4

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4.database.entities.Schedule
import java.sql.Date
import java.text.SimpleDateFormat

class BusStopAdapter(private val initData: List<Schedule>, private val clickListener: (Schedule)->Unit)
    : RecyclerView.Adapter<BusStopAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val stopName: TextView by lazy { itemView.findViewById(R.id.stopName) }
        val arrivalTime: TextView by lazy { itemView.findViewById(R.id.arrivalTime) }
    }

    private val displayedData: ArrayList<Schedule> = ArrayList<Schedule>()
    init {
        displayedData.addAll(initData)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newData: List<Schedule>){
        this.displayedData.clear()
        this.displayedData.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.schedule_item_display, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return displayedData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = this.displayedData[position]
        val sdf = SimpleDateFormat("HH:MM")
        holder.stopName.text = item.stopName
        holder.arrivalTime.text = sdf.format(Date(item.arrivalTime.toLong()))

        holder.itemView.setOnClickListener { clickListener(item) }
    }
}