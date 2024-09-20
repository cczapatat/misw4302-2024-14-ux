package com.uniandes.edu.uxalarm

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.uniandes.edu.uxalarm.adapters.AlarmItemAdapter
import com.uniandes.edu.uxalarm.databinding.ActivityCreateAlarmBinding

class CreateAlarmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAlarmBinding

    private var currentIndex = arrayOf(0, 1, 2).random()
    private var currentIndexHours = 0
    private var currentIndexMinutes = 0
    private var currentIndexZones = 0
    private val images =
        intArrayOf(R.drawable.reloj_base, R.drawable.reloj_smart, R.drawable.reloj_sport)

    private val hours = Array(12) {
        val hour = it + 1
        if (hour < 10) {
            "0$hour"
        } else {
            hour.toString()
        }
    }
    private val minutes = Array(60) {
        if (it < 10) {
            "0$it"
        } else {
            it.toString()
        }
    }
    private val zones = arrayOf("am", "pm")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hour = hours.random()
        currentIndexHours = hours.indexOf(hour)
        binding.txtHours.text = hour

        val minute = minutes.random()
        currentIndexMinutes = minutes.indexOf(minute)
        binding.txtMinutes.text = minute

        val zone = zones.random()
        currentIndexZones = zones.indexOf(zone)
        binding.txtZone.text = zone

        updateImage()

        // Relojs
        binding.leftButton.setOnClickListener {
            currentIndex -= 1

            if (currentIndex < 0) {
                currentIndex = images.size - 1
            }
            animateImageChange()
        }

        binding.rightButton.setOnClickListener {
            currentIndex += 1

            if (currentIndex >= images.size) {
                currentIndex = 0
            }
            animateImageChange()
        }

        // Hours
        binding.btnHourDown.setOnClickListener {
            currentIndexHours -= 1

            if (currentIndexHours < 0) {
                currentIndexHours = hours.size - 1
            }
            binding.txtHours.text = hours[currentIndexHours]
        }

        binding.btnHourUp.setOnClickListener {
            currentIndexHours += 1

            if (currentIndexHours >= hours.size) {
                currentIndexHours = 0
            }
            binding.txtHours.text = hours[currentIndexHours]
        }

        // Minutes
        binding.btnMinutesDown.setOnClickListener {
            currentIndexMinutes -= 1

            if (currentIndexMinutes < 0) {
                currentIndexMinutes = minutes.size - 1
            }
            binding.txtMinutes.text = minutes[currentIndexMinutes]
        }

        binding.btnMinutesUp.setOnClickListener {
            currentIndexMinutes += 1

            if (currentIndexMinutes >= minutes.size) {
                currentIndexMinutes = 0
            }
            binding.txtMinutes.text = minutes[currentIndexMinutes]
        }

        // Zones
        binding.btnZoneDown.setOnClickListener {
            currentIndexZones -= 1

            if (currentIndexZones < 0) {
                currentIndexZones = zones.size - 1
            }
            binding.txtZone.text = zones[currentIndexZones]
        }

        binding.btnZoneUp.setOnClickListener {
            currentIndexZones += 1

            if (currentIndexZones >= zones.size) {
                currentIndexZones = 0
            }
            binding.txtZone.text = zones[currentIndexZones]
        }

        // Buttons
        binding.btnCustomAlarm.setOnClickListener {
            startActivity(Intent(applicationContext, PersonalizarAlarmaActivity::class.java))
        }

        binding.btnSave.setOnClickListener { finish() }

        binding.btnCancel.setOnClickListener { finish() }
    }

    private fun updateImage() {
        binding.imageView.foreground = ContextCompat.getDrawable(
            applicationContext, images[currentIndex]
        )
    }

    private fun animateImageChange() {
        val fadeOut = ObjectAnimator.ofFloat(binding.imageView, "alpha", 1f, 0f).apply {
            duration = 250
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    updateImage()
                    ObjectAnimator.ofFloat(binding.imageView, "alpha", 0f, 1f).apply {
                        duration = 250
                    }.start()
                }
            })
        }
        fadeOut.start()
    }
}