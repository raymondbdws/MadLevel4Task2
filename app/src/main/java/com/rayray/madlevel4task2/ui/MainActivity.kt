package com.rayray.madlevel4task2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.rayray.madlevel4task2.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        navController = findNavController(R.id.nav_host_fragment)

//        val historyBtn = findViewById<Button>(R.id.btnHistory)
//
//        historyBtn.setOnClickListener {
//            navController.navigate(
//                R.id.action_playFragment_to_historyFragment
//            )
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        navController.navigate(
            R.id.action_playFragment_to_historyFragment
        )
        return when (item.itemId) {
            R.id.btnHistory -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}