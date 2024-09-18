package com.uniandes.edu.uxalarm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uniandes.edu.uxalarm.databinding.ActivityCreateAlarmBinding

class CreateAlarmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}