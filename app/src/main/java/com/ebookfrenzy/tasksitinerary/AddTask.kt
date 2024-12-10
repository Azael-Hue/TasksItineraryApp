package com.ebookfrenzy.tasksitinerary

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Spinner
import android.view.ViewGroup
import android.widget.EditText
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.example.tasksitinerary.com.ebookfrenzy.tasksitinerary.Task
import com.ebookfrenzy.tasksitinerary.databinding.FragmentAddTaskBinding
import com.example.tasksitinerary.com.ebookfrenzy.tasksitinerary.MyDBHandler

class AddTask : Fragment() {

    private lateinit var myDBHandler: MyDBHandler
    private lateinit var taskNameEditText: EditText
    private lateinit var importanceSpinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = FragmentAddTaskBinding.inflate(inflater, container, false)

        // Initialize the views and the DB handler
        taskNameEditText = binding.editTextTaskName
        importanceSpinner = binding.spinnerTaskImportnace
        myDBHandler = MyDBHandler(requireContext(), null, null, 1)

        // Set the onClickListener for the Add Task button
        binding.btnAddTask.setOnClickListener {
            addTask(it)
        }

        return binding.root
    }

    // The addTask function to be triggered by the button click
    fun addTask(view: View) {
        val taskName = taskNameEditText.text.toString()
        val importance = importanceSpinner.selectedItem.toString()

        if (taskName.isNotEmpty()) {
            // Create a Task object and add it to the database
            val task = Task(0, taskName, importance)
            myDBHandler.addTask(task)

            // clear the input fields after adding the task
            taskNameEditText.text.clear()
            importanceSpinner.setSelection(0)  // Reset to first item (Low)

            Toast.makeText(requireContext(), "Task added successfully!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Please enter a task name.", Toast.LENGTH_SHORT).show()
        }
    }
}