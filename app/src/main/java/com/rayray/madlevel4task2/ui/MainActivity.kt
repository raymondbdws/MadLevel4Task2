package com.rayray.madlevel4task2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.rayray.madlevel4task2.R
import com.rayray.madlevel4task2.repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var menu: Menu
    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        gameRepository = GameRepository(applicationContext)
        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        this.menu = menu
        return true
    }

    /**
     * Dit is een tijdelijke oplossing, crasht wanneer je vanaf history > home pagina op de backbutton klikt
     * Verwijdert menu
     * voegt nieuw menu toe
     * opent nieuwe fragment
     */
    override fun onBackPressed() {
        //delete old menu
        menu.clear()

        //add new menu
        menuInflater.inflate(R.menu.menu_main, menu)

        //display fragment
        navController.navigate(
            R.id.action_historyFragment_to_playFragment
        )
    }

    /**
     * When item is selected or user pressed a button, then it checks what need to be done.
     *
     *
     * @param item selected item in menu
     * @return
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (item.itemId == R.id.btnHistory || item.itemId == android.R.id.home) {
            //Delete old menu
            menu.clear()

            navController.navigate(
                //Back Arrow button
                if (navController.currentDestination?.id == R.id.historyFragment) {
                    //add new menu to actionbar
                    menuInflater.inflate(R.menu.menu_main, menu)
                    //display new fragment
                    R.id.action_historyFragment_to_playFragment
                } else {
                    //History button
                    menuInflater.inflate(R.menu.menu_history, menu)
                    R.id.action_playFragment_to_historyFragment
                }
            )
        } else {
            //Delete history button
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    //Delete history
                    gameRepository.deleteAllGames()
                    //TODO Kan recycleview niet verversen vanuit MainAcitity >> moet in HistoryFragement
                }
            }
            Toast.makeText(
                applicationContext,
                "Successful, please reload this page",
                Toast.LENGTH_LONG
            ).show()
        }

        return when (item.itemId) {
            R.id.btnHistory -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}