package com.uniandes.edu.uxalarm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uniandes.edu.uxalarm.databinding.ActivityScanQrBinding

class ScanQRActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanQrBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanQrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCloseScanQr.setOnClickListener {
            finish()
        }
    }
}