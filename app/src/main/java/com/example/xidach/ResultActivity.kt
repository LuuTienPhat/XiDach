package com.example.xidach

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xidach.adaper.PlayerAdapter
import com.example.xidach.adaper.ResultAdapter
import com.example.xidach.databinding.ActivityGameBinding
import com.example.xidach.databinding.ActivityResultBinding
import com.example.xidach.models.Player

class ResultActivity : AppCompatActivity() {

    //private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var binding: ActivityResultBinding
    private var playerList: List<Player> = ArrayList<Player>()
    private var dealer: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dealer = intent.getStringExtra("dealer")
        playerList = intent.getSerializableExtra("playerList") as List<Player>
        binding.rvPlayerList.adapter = ResultAdapter(playerList as ArrayList<Player>, dealer!!)

        binding.btnMenu.setOnClickListener(View.OnClickListener {
            var intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        })

//        recyclerView = binding.rvPlayerList;
//        recyclerView.layoutManager = LinearLayoutManager(this);
//
//        val data = ArrayList<Player>();
//        data.add(Player("Han", 5))
//        data.add(Player("Linh", 5))
//        data.add(Player("Phu", 5))
//        data.add(Player("Phuong", 5))
//
//        val adapter = PlayerAdapter(data)
//        recyclerView.adapter = adapter

        //setSupportActionBar(binding.toolbar)

//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//
//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAnchorView(R.id.fab)
//                    .setAction("Action", null).show()
//        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}