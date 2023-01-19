package com.example.verga

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.verga.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.search.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            // start your next activity
            startActivity(intent)
        }

        val navController = this.findNavController(R.id.fragmentContainerView)
        // Find reference to bottom navigation view
        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        // Hook your navigation controller to bottom navigation view
        navView.setupWithNavController(navController)


    }
}