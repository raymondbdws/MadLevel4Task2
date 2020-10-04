package com.rayray.madlevel4task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rayray.madlevel4task2.R
import com.rayray.madlevel4task2.model.Game
import com.rayray.madlevel4task2.model.GameElementsEnum
import com.rayray.madlevel4task2.repository.GameRepository
import kotlinx.android.synthetic.main.fragment_play.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

/**
 * @author Raymond Chang
 *
 * Fragment Play
 */
class PlayFragment : Fragment() {
    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "MadLevel4Task2"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        gameRepository = GameRepository(requireContext())

        //Zonder deze code, verandert alle 3 afbeeldingen naar steen. idk waarom
        ivRock.setImageResource(R.drawable.rock)
        ivPaper.setImageResource(R.drawable.paper)
        ivScissors.setImageResource(R.drawable.scissors)

        ivRock.setOnClickListener {
            startGame(GameElementsEnum.ROCK)
        }
        ivPaper.setOnClickListener {
            startGame(GameElementsEnum.PAPER)
        }
        ivScissors.setOnClickListener {
            startGame(GameElementsEnum.SCISSER)
        }
    }

    /**
     * Use it, to start the game
     *
     * @param user users answer
     */
    fun startGame(user: GameElementsEnum) {
        //computer antwoord bepalen
        val computer = computerAnswer()

        //weergeven
        ivComputer.setImageResource(computer.element)
        ivYou.setImageResource(user.element)

        //opslaan
        //todo mainscope uitleg
        //todo coroutinescope uitleg
        //To specify where the coroutines should run, Kotlin provides three Dispatchers you can use. The dispatchers being:
        //Dispatchers.Main: Main thread on Android, interact with the UI and perform light work.
        //Dispatchers.IO: Optimized for disk and network IO.
        //Dispatchers.Default: Optimized for CPU intensive work.
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.insertGame(
                    Game(
                        computer.element,
                        user.element,
                        Date(),
                        getWinner(computer, user)
                    )
                )
            }
        }
    }

    /**
     * generate computers answer
     */
    fun computerAnswer(): GameElementsEnum {
        return GameElementsEnum.values().toList().shuffled().get(0)
    }

    /**
     * Goes through the if statement to determine the winner and loser and it will display
     * the winner or draw on the screen
     *
     * @param return strings
     */
    private fun getWinner(computer: GameElementsEnum, user: GameElementsEnum): String {

        if (
        //checks if computer wins

            computer == GameElementsEnum.ROCK && user == GameElementsEnum.SCISSER ||
            computer == GameElementsEnum.PAPER && user == GameElementsEnum.ROCK ||
            computer == GameElementsEnum.SCISSER && user == GameElementsEnum.PAPER
        ) {
            //if true, then do this

            mainScope.launch {
                withContext(Dispatchers.Main) {
                    tvWinner.setText(R.string.computerWins)
                }
            }

            return "Computer wins!"
        } else if (
        //checks of user win

            computer == GameElementsEnum.ROCK && user == GameElementsEnum.PAPER ||
            computer == GameElementsEnum.PAPER && user == GameElementsEnum.SCISSER ||
            computer == GameElementsEnum.SCISSER && user == GameElementsEnum.ROCK
        ) {
            //if true, then do this

            mainScope.launch {
                withContext(Dispatchers.Main) {
                    tvWinner.setText(R.string.youWins)
                }
            }
            return "You win!"
        } else {
            //otherwise it is a draw

            mainScope.launch {
                withContext(Dispatchers.Main) {
                    tvWinner.setText(R.string.draw)
                }
            }
            return "Draw"
        }
    }
}