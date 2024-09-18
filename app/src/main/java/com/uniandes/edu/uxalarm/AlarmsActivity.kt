package com.uniandes.edu.uxalarm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uniandes.edu.uxalarm.databinding.ActivityAlarmsBinding

class AlarmsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlarmsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOpenScanQr.setOnClickListener {
            val intent = Intent(applicationContext, ScanQRActivity::class.java)
            startActivity(intent)
        }
    }
}