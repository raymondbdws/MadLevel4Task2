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
 * A simple [Fragment] subclass as the default destination in the navigation.
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

    fun startGame(user: GameElementsEnum) {
        //computer antwoord bepalen
        val computer = computerAnswer()

        //weergeven
        ivComputer.setImageResource(computer.element)
        ivYou.setImageResource(user.element)

        //opslaan
        //todo mainscope uitleg
        //todo coroutinescope uitleg
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.insertGame(
                    Game(
                        computer.element,
                        user.element,
                        Date(3102020),
                        getWinner(computer, user)
                    )
                )
            }
        }
    }

    fun computerAnswer(): GameElementsEnum {
        return GameElementsEnum.values().toList().shuffled().get(0)
    }

    private fun getWinner(computer: GameElementsEnum, user: GameElementsEnum): String {

        if (
            computer.equals(GameElementsEnum.ROCK) && user.equals(GameElementsEnum.SCISSER) ||
            computer.equals(GameElementsEnum.PAPER) && user.equals(GameElementsEnum.ROCK) ||
            computer.equals(GameElementsEnum.SCISSER) && user.equals(GameElementsEnum.PAPER)
        ) {
            mainScope.launch {
                withContext(Dispatchers.Main) {
                    tvWinner.setText(R.string.computerWins)
                }
            }

            return "Computer wins!"
        } else if (
            computer.equals(GameElementsEnum.ROCK) && user.equals(GameElementsEnum.PAPER) ||
            computer.equals(GameElementsEnum.PAPER) && user.equals(GameElementsEnum.SCISSER) ||
            computer.equals(GameElementsEnum.SCISSER) && user.equals(GameElementsEnum.ROCK)
        ) {
            mainScope.launch {
                withContext(Dispatchers.Main) {
                    tvWinner.setText(R.string.youWins)
                }
            }
            return "You win!"
        } else {
            mainScope.launch {
                withContext(Dispatchers.Main) {
                    tvWinner.setText(R.string.draw)
                }
            }
            return "Draw"
        }
    }

}