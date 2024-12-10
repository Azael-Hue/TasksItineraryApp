package com.ebookfrenzy.tasksitinerary

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.ImageView
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.tasksitinerary.com.ebookfrenzy.tasksitinerary.Task
import com.example.tasksitinerary.com.ebookfrenzy.tasksitinerary.MyDBHandler

class RecyclerAdapter(
    private val taskList: MutableList<Task>,
    private val dbHandler: MyDBHandler
    ) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    // ViewHolder to bind task data to views
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskNameCheckBox: CheckBox = itemView.findViewById(R.id.checkBoxTaskName)
        val taskImportance: TextView = itemView.findViewById(R.id.textViewCardImportance)
    }

    // called by the RecyclerView to obtain a ViewHolder object.
    // It inflates the view hierarchy task_layout.xml file and creates an
    // instance of our ViewHolder class initialized with the view hierarchy.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).
            inflate(R.layout.task_layout, parent, false)
        return ViewHolder(view)
    }

    // populates the view hierarchy within the
    // ViewHolder object with the data to be displayed
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = taskList[position]
        holder.taskNameCheckBox.text = task.taskName
        holder.taskImportance.text = task.taskImportance

        // Add long-click listener for deleting the task
        holder.itemView.setOnLongClickListener {
            // Delete the task from the database
            val isDeleted = dbHandler.deleteTask(task.taskName.toString())
            if (isDeleted) {
                taskList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, taskList.size)
            }
            true
        }
    }

    // return the total number of tasks in the array
    override fun getItemCount(): Int {
        return taskList.size
    }
}