package com.example.xidach.adaper

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xidach.databinding.ItemPlayerBinding
import com.example.xidach.models.Player
import com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener
import kotlinx.android.synthetic.main.item_player.view.*


class PlayerAdapter(private var playerList: ArrayList<Player>) :
    RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    private lateinit var listener: PlayerAdapter.OnPlayerAdapterToggleClickListener
    var status: String = "normal"

    constructor(playerList: ArrayList<Player>, context: Context) : this(playerList) {
        this.listener = context as OnPlayerAdapterToggleClickListener
    }

    inner class ViewHolder(private val binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Player) {
            binding.toggleButton.clearChecked()
            binding.playerName.text = player.name
            binding.txtScore.text = player.score.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return ViewHolder(binding);
    }

    override fun getItemCount(): Int {
        return playerList.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player: Player = playerList[position];
        holder.bind(player);
        holder.itemView.toggleButton.addOnButtonCheckedListener(createOnButtonCheckedListener(holder, player))
    }

    fun createOnButtonCheckedListener(holder: ViewHolder, player: Player): OnButtonCheckedListener {
        return OnButtonCheckedListener { group, checkedId, isChecked ->
            var newScore: Int = 0
            if (isChecked) {
                if (checkedId == holder.itemView.btnDown.id) {
                    newScore = player.score.minus(player.bets)
                    //binding.btnDown.isEnabled = false
                } else {
                    newScore = player.score.plus(player.bets)
                    //binding.btnUp.isEnabled = true
                }
            } else {
                if (checkedId == holder.itemView.btnDown.id) {
                    newScore = player.score.plus(player.bets)
                    //binding.btnDown.isEnabled = false
                } else {
                    newScore = player.score.minus(player.bets)
                    //binding.btnUp.isEnabled = true
                }
            }
            //listener.onToggleClicked(adapterPosition, newScore)
            player.score = newScore
            holder.itemView.txtScore.text = newScore.toString()

        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
    }

    interface OnPlayerAdapterToggleClickListener {
        fun onToggleClicked(position: Int, score: Int)
    }
}