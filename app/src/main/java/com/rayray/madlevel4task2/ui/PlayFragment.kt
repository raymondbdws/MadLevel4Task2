package com.rayray.madlevel4task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.rayray.madlevel4task2.R
import com.rayray.madlevel4task2.model.GameElementsEnum
import kotlinx.android.synthetic.main.fragment_play.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PlayFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    fun startGame(user: GameElementsEnum){
        val computer = computerAnswer()

        ivComputer.setImageResource(computer.element)
        ivYou.setImageResource(user.element)
    }

    fun computerAnswer(): GameElementsEnum{
        return GameElementsEnum.values().toList().shuffled().get(0)
    }
}