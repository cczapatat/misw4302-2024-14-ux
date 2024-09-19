package com.uniandes.edu.uxalarm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uniandes.edu.uxalarm.databinding.ActivityEditAlarmBinding

class EditAlarmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}