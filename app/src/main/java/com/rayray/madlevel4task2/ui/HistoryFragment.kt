package com.rayray.madlevel4task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rayray.madlevel4task2.R
import com.rayray.madlevel4task2.model.Game
import com.rayray.madlevel4task2.model.GameElementsEnum
import com.rayray.madlevel4task2.model.GameStatusEnum
import kotlinx.android.synthetic.main.fragment_history.*
import java.time.LocalDateTime
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HistoryFragment : Fragment() {

    private val historyList = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(historyList)

    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.button_second).setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
        initViews()

        historyList.add(
            Game(
                GameElementsEnum.ROCK.element,
                GameElementsEnum.PAPER.element,
                Date(23),
                GameStatusEnum.YOU.status
            )
        )
        historyList.add(
            Game(
                R.drawable.scissors,
                R.drawable.rock,
                Date(23),
                GameStatusEnum.YOU.status
            )
        )
        historyList.add(
            Game(
                R.drawable.scissors,
                R.drawable.rock,
                Date(23),
                GameStatusEnum.YOU.status
            )
        )
        gameAdapter.notifyDataSetChanged()
    }

    private fun initViews() {
        viewManager = LinearLayoutManager(activity)
        rvGameHistory.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )

        rvGameHistory.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = gameAdapter
        }
    }
}