package com.example.xidach

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.xidach.databinding.ActivityMenuBinding
import com.google.android.material.textfield.TextInputLayout

class MenuActivity : AppCompatActivity() {

    //private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMenuBinding
    private lateinit var txtDealer : TextInputLayout
    private lateinit var txtBets: TextInputLayout
    private lateinit var txtPlayers: TextInputLayout
    private lateinit var btnStart : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        txtDealer = binding.txtDealer
        txtPlayers = binding.txtPlayers
        txtBets = binding.txtBets
        btnStart = binding.btnStart;

        btnStart.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("dealer", txtDealer.editText?.text.toString())
            intent.putExtra("players", txtPlayers.editText?.text.toString())
            intent.putExtra("bets", txtBets.editText?.text.toString())
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivity(intent)
        })

//        setSupportActionBar(binding.toolbar)
//
//        val navController = findNavController(R.id.nav_host_fragment_content_menu)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//
//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAnchorView(R.id.fab).setAction("Action", null).show()
//        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_menu)
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }
}