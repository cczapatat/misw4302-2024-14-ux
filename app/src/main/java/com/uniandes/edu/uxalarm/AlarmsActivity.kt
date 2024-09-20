package com.uniandes.edu.uxalarm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uniandes.edu.uxalarm.adapters.AlarmItemAdapter
import com.uniandes.edu.uxalarm.databinding.ActivityAlarmsBinding

class AlarmsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlarmsBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AlarmItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOpenScanQr.setOnClickListener {
            startActivity(Intent(applicationContext, ScanQRActivity::class.java))
        }

        binding.btnCreateAlarm.setOnClickListener {
            startActivity(Intent(applicationContext, CreateAlarmActivity::class.java))
        }

        recyclerView = binding.recyclerViewAlarms
        recyclerView.layoutManager = LinearLayoutManager(this)

        val images = List(10) {
            intArrayOf(R.drawable.reloj_base, R.drawable.reloj_smart, R.drawable.reloj_sport)
        }
        adapter = AlarmItemAdapter(images)
        recyclerView.adapter = adapter
    }
}