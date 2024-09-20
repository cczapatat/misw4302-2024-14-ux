package com.uniandes.edu.uxalarm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uniandes.edu.uxalarm.adapters.AlarmItemAdapter
import com.uniandes.edu.uxalarm.databinding.ActivityAlarmsBinding
import com.uniandes.edu.uxalarm.dtos.AlarmItem

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

        val titles = arrayListOf(
            "12:20 a.m.",
            "Iniciar mi día",
            "Pausa",
            "Dormir",
            "06:30 p.m."
        )
        val times = arrayListOf(
            "12:00 a.m.",
            "05:10 p.m.",
            "07:30 a.m.",
            "02:45 p.m.",
            "08:45 a.m.",
            "10:35 a.m.",
        )
        val disables = arrayListOf(true, false)

        val images = Array(10) {
            val title = titles.random()
            AlarmItem(
                title = title,
                time = if (title.contains(":")) {
                    title
                } else {
                    times.random()
                },
                disable = disables.random(),
                clockIndex = arrayOf(0, 1, 2).random(),
                images = intArrayOf(
                    R.drawable.reloj_base,
                    R.drawable.reloj_smart,
                    R.drawable.reloj_sport
                )
            )
        }
        adapter = AlarmItemAdapter(images)
        recyclerView.adapter = adapter
    }
}