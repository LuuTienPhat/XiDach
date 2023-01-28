package com.example.xidach.adaper

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xidach.databinding.ItemPlayerBinding
import com.example.xidach.databinding.ItemResultBinding
import com.example.xidach.models.Player


class ResultAdapter(private var playerList: ArrayList<Player>, private var dealer: String) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Player) {
            if (player.score < 0) {
                binding.txtPlayerName1.text = player.name
                binding.txtPlayerName2.text = dealer
                binding.txtScore.text = player.score.toString()
            } else {
                binding.txtPlayerName1.text = dealer
                binding.txtPlayerName2.text = player.name
                binding.txtScore.text = player.score.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return ViewHolder(binding);
    }

    override fun getItemCount(): Int {
        return playerList.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player: Player = playerList[position];
        holder.bind(player);
    }
}