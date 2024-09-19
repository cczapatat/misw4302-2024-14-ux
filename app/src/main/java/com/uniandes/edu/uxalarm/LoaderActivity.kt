package com.uniandes.edu.uxalarm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uniandes.edu.uxalarm.databinding.ActivityLoaderBinding

class LoaderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoaderBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Thread(Runnable {
            var iterations = 0
            while (iterations < 3) {
                Thread.sleep(600)
                iterations += 1
                binding.imgLoader.rotation = (iterations * 30F)
            }

            val intent = Intent(applicationContext, AlarmsActivity::class.java)
            startActivity(intent)
            finish()
        }).start()
    }
}