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

    private var currentIndex = 0
    private val images =
        intArrayOf(R.drawable.reloj_base, R.drawable.reloj_smart, R.drawable.reloj_sport)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateImage()

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