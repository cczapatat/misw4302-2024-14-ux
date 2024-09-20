package com.uniandes.edu.uxalarm

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.uniandes.edu.uxalarm.databinding.ActivityEditAlarmBinding

class EditAlarmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditAlarmBinding

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
        binding = ActivityEditAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hour = hours.random()
        currentIndexHours = hours.indexOf(hour)
        binding.txtHoursEdit.text = hour

        val minute = minutes.random()
        currentIndexMinutes = minutes.indexOf(minute)
        binding.txtMinutesEdit.text = minute

        val zone = zones.random()
        currentIndexZones = zones.indexOf(zone)
        binding.txtZoneEdit.text = zone

        updateImage()

        // Relojs
        binding.leftButtonEdit.setOnClickListener {
            currentIndex -= 1

            if (currentIndex < 0) {
                currentIndex = images.size - 1
            }
            animateImageChange()
        }

        binding.rightButtonEdit.setOnClickListener {
            currentIndex += 1

            if (currentIndex >= images.size) {
                currentIndex = 0
            }
            animateImageChange()
        }

        // Hours
        binding.btnHourDownEdit.setOnClickListener {
            currentIndexHours -= 1

            if (currentIndexHours < 0) {
                currentIndexHours = hours.size - 1
            }
            binding.txtHoursEdit.text = hours[currentIndexHours]
        }

        binding.btnHourUpEdit.setOnClickListener {
            currentIndexHours += 1

            if (currentIndexHours >= hours.size) {
                currentIndexHours = 0
            }
            binding.txtHoursEdit.text = hours[currentIndexHours]
        }

        // Minutes
        binding.btnMinutesDownEdit.setOnClickListener {
            currentIndexMinutes -= 1

            if (currentIndexMinutes < 0) {
                currentIndexMinutes = minutes.size - 1
            }
            binding.txtMinutesEdit.text = minutes[currentIndexMinutes]
        }

        binding.btnMinutesUpEdit.setOnClickListener {
            currentIndexMinutes += 1

            if (currentIndexMinutes >= minutes.size) {
                currentIndexMinutes = 0
            }
            binding.txtMinutesEdit.text = minutes[currentIndexMinutes]
        }

        // Zones
        binding.btnZoneDownEdit.setOnClickListener {
            currentIndexZones -= 1

            if (currentIndexZones < 0) {
                currentIndexZones = zones.size - 1
            }
            binding.txtZoneEdit.text = zones[currentIndexZones]
        }

        binding.btnZoneUpEdit.setOnClickListener {
            currentIndexZones += 1

            if (currentIndexZones >= zones.size) {
                currentIndexZones = 0
            }
            binding.txtZoneEdit.text = zones[currentIndexZones]
        }

        // Buttons
        binding.btnCustomAlarmEdit.setOnClickListener {
            startActivity(Intent(applicationContext, PersonalizarAlarmaActivity::class.java))
        }

        binding.btnSaveEdit.setOnClickListener { finish() }

        binding.btnCancelEdit.setOnClickListener { finish() }
    }

    private fun updateImage() {
        binding.imageViewEdit.foreground = ContextCompat.getDrawable(
            applicationContext, images[currentIndex]
        )
    }

    private fun animateImageChange() {
        val fadeOut = ObjectAnimator.ofFloat(binding.imageViewEdit, "alpha", 1f, 0f).apply {
            duration = 250
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    updateImage()
                    ObjectAnimator.ofFloat(binding.imageViewEdit, "alpha", 0f, 1f).apply {
                        duration = 250
                    }.start()
                }
            })
        }
        fadeOut.start()
    }
}