package com.uniandes.edu.uxalarm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uniandes.edu.uxalarm.databinding.ActivityPersonalizarAlarmaBinding

class PersonalizarAlarmaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonalizarAlarmaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalizarAlarmaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.personalizarAlarmaCancelar.setOnClickListener { finish() }
    }
}