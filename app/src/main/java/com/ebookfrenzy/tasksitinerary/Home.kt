package com.ebookfrenzy.tasksitinerary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasksitinerary.com.ebookfrenzy.tasksitinerary.MyDBHandler

class Home : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var dbHandler: MyDBHandler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize RecyclerView and Database Handler
        recyclerView = view.findViewById(R.id.recyclerView)
        dbHandler = MyDBHandler(requireContext(), null, null, 1)

        // Fetch tasks from the database
        val taskList = dbHandler.getAllTasks()

        // Set up RecyclerView
        recyclerAdapter = RecyclerAdapter(taskList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdapter

        return view
    }
}
