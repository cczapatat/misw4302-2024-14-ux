package com.uniandes.edu.uxalarm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.uniandes.edu.uxalarm.databinding.ActivityPersonalizarAlarmaBinding

class PersonalizarAlarmaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonalizarAlarmaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalizarAlarmaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vibrarSwitch.setOnCheckedChangeListener { _, b ->
            binding.vibrarSwitch.thumbDrawable = ContextCompat.getDrawable(
                applicationContext,
                if (b) {
                    R.drawable.handle_check
                } else {
                    R.drawable.handle_shape
                }
            )
        }

        binding.switch2.setOnCheckedChangeListener { _, b ->
            binding.switch2.thumbDrawable = ContextCompat.getDrawable(
                applicationContext,
                if (b) {
                    R.drawable.handle_check
                } else {
                    R.drawable.handle_shape
                }
            )
        }

        binding.personalizarAlarmaGuardar.setOnClickListener {
            val intent = Intent(application.baseContext, AlarmsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        binding.personalizarAlarmaCancelar.setOnClickListener { finish() }
    }
}