package com.example.xidach

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xidach.adaper.PlayerAdapter
import com.example.xidach.databinding.ActivityGameBinding
import com.example.xidach.models.Player
import com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener
import kotlinx.android.synthetic.main.item_player.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class GameActivity : AppCompatActivity(), PlayerAdapter.OnPlayerAdapterToggleClickListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    interface OnGameActivityNextRoundListener {
        fun onNextRoundListener()
    }

    private lateinit var binding: ActivityGameBinding
    private lateinit var recyclerView: RecyclerView;
    private var bets: Int? = null
    private var players: String? = null
    private var dealer: String? = null
    private var playerList: ArrayList<Player> = ArrayList<Player>()
    private var dealerScore: Int? = null
    private var round: Int = 0
    private lateinit var listener: GameActivity.OnGameActivityNextRoundListener
    private lateinit var adapter: PlayerAdapter

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //listener = this as OnGameActivityNextRoundListener

        dealer = intent.getStringExtra("dealer")
        players = intent.getStringExtra("players")
        bets = intent.getStringExtra("bets")?.toInt()

        val data = players?.split(",")
        if (data != null) {
            for (item: String in data) {
                bets?.let { Player(item, 0, it) }?.let { playerList.add(it) }
            }
        }
        recyclerView = binding.rvPlayerList;
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PlayerAdapter(playerList, this)
        recyclerView.adapter = adapter

        binding.txtDealer.text = dealer

        var vietnam = Locale("vi ", "VN")
        val dongs: Currency = Currency.getInstance(vietnam)
        val dongsFormat: NumberFormat = NumberFormat.getCurrencyInstance(vietnam)
        binding.txtBets.text = "${dongsFormat.format(bets)}"
        binding.txtRound.text = "R$round"

        binding.btnFinish.setOnClickListener(View.OnClickListener {
            var intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("playerList", playerList)
            intent.putExtra("dealer", dealer)
            intent.putExtra("bets", bets)
            startActivity(intent)
        })

        binding.btnNextRound.setOnClickListener(View.OnClickListener {
            increaseRound()

            for (i in 0 until playerList.size step 1) {
                var viewHolder: PlayerAdapter.ViewHolder =
                    binding.rvPlayerList.findViewHolderForAdapterPosition(i) as PlayerAdapter.ViewHolder
                viewHolder.itemView.toggleButton.clearOnButtonCheckedListeners()
                viewHolder.itemView.toggleButton.clearChecked()
                viewHolder.itemView.toggleButton.addOnButtonCheckedListener(
                    adapter.createOnButtonCheckedListener(
                        viewHolder, playerList[i]
                    )
                )
            }

//            adapter.status = "changed"
//            adapter.notifyDataSetChanged()
//            adapter.status = "normal"
        })

        setSupportActionBar(binding.toolbar)

//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAnchorView(R.id.fab)
//                    .setAction("Action", null).show()
//        }
    }

    override fun onToggleClicked(position: Int, score: Int) {
        val player: Player = playerList[position]
        player.score = score;
        playerList[position] = player
        adapter.notifyItemChanged(position)

//        bets = bets?.plus(score)
//        binding.txtBets.text = bets.toString()

//        if(score < 0) {
//            bets = bets?.plus(score)
//            binding.txtBets.text = bets.toString()
//        }
//        else if(score == 0){
//
//        }
//        else {
//            bets = bets?.plus( score)
//            binding.txtBets.text = bets.toString()
//        }
    }

    @SuppressLint("SetTextI18n")
    private fun increaseRound(): Int {
        round = round.inc()
        binding.txtRound.text = "R$round"
        return round;
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
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }
}