package com.uniandes.edu.uxalarm

import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.uniandes.edu.uxalarm.databinding.ActivityExecutionAlarmBinding


class ExecutionAlarmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExecutionAlarmBinding
    private lateinit var relojSoundAnimation: AnimationDrawable
    private lateinit var micAnimation: AnimationDrawable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExecutionAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setLinearGradiant()
        addListenerOnButton(binding.cancelButton, binding.cancelProgressBar)
        addListenerOnButton(binding.checkButton, binding.checkProgressBar)

        binding.relojSoundButton.setBackgroundResource(R.drawable.reloj_sound_animation)
        binding.micButton.setBackgroundResource(R.drawable.mic_animation)
        relojSoundAnimation = binding.relojSoundButton.background as AnimationDrawable
        micAnimation = binding.micButton.background as AnimationDrawable
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        relojSoundAnimation.start()
        micAnimation.start()
    }

    private fun setLinearGradiant() {
        val width: Float = binding.micButtonDescription.paint.measureText(
            binding.micButtonDescription.text.toString()
        )
        val textSize = binding.micButtonDescription.textSize

        val shader: Shader = LinearGradient(
            0.0f, 0.0f, width*0.20f, textSize,
            getColor(R.color.negro_app), getColor(R.color.azul_oscuro_app), Shader.TileMode.REPEAT
        )
        binding.micButtonDescription.paint.shader = shader
    }

    fun addListenerOnButton(button: ImageButton, progressBar: ProgressBar) {
        button.setOnTouchListener(OnTouchListener {
                view, motionEvent ->

            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    progressBar.max = 1000

                    while (progressBar.progress < progressBar.max) {
                        progressBar.progress = progressBar.progress + 1
                    }

                    if (progressBar.progress == progressBar.max) {
                        //BACK TO ALARM LIST VIEW
                    }
                }

                MotionEvent.ACTION_UP -> {
                    progressBar.progress = 0
                }
            }





                true
        })


    }
}
