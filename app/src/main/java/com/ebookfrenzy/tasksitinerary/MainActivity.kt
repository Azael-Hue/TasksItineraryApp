package com.ebookfrenzy.tasksitinerary

import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.activity.enableEdgeToEdge
import androidx.navigation.findNavController
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.setupWithNavController
import com.ebookfrenzy.tasksitinerary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // use databinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home()) // start on the Home Fragment

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up a listener, when a button is pressed from the nav menu
        // it will call the replace fragment method
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(Home())
                R.id.addTask -> replaceFragment(AddTask())
            }
            true
        }
    }

    // This method replaces the fragment depending on which
    // one was pressed passing through the fragment
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}