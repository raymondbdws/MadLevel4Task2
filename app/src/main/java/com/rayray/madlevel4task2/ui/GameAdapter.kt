package com.rayray.madlevel4task2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rayray.madlevel4task2.R
import com.rayray.madlevel4task2.model.Game
import kotlinx.android.synthetic.main.item_history.view.*
import kotlinx.android.synthetic.main.item_history.view.tvGameResult

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun databind(game: Game) {
            itemView.tvGameResult.text = game.result
            itemView.tvDate.text = game.date.toString()
            itemView.ivComputerHistory.setImageResource(game.computer)
            itemView.ivYouHistory.setImageResource(game.user)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_history,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }
}