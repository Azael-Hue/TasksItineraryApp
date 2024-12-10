package com.ebookfrenzy.tasksitinerary

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.ImageView
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.tasksitinerary.com.ebookfrenzy.tasksitinerary.Task

class RecyclerAdapter(private val taskList: List<Task>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    // ViewHolder to bind task data to views
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskNameCheckBox: CheckBox = itemView.findViewById(R.id.checkBoxTaskName)
        val taskImportance: TextView = itemView.findViewById(R.id.textViewCardImportance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = taskList[position]
        holder.taskNameCheckBox.text = task.taskName
        holder.taskImportance.text = task.taskImportance
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}