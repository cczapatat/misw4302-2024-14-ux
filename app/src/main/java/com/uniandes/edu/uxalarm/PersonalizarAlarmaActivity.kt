package com.uniandes.edu.uxalarm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.uniandes.edu.uxalarm.databinding.ActivityPersonalizarAlarmaBinding


class PersonalizarAlarmaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonalizarAlarmaBinding
    private val frequencyOptionsLabels = arrayOf("Una vez", "Diario", "Semanal", "Dinamico")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalizarAlarmaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dynamicFrequencyDaysCheckBoxes = arrayOf(
            binding.checkBoxL,
            binding.checkBoxM,
            binding.checkBoxMi,
            binding.checkBoxJ,
            binding.checkBoxV,
            binding.checkBoxS,
            binding.checkBoxD,
        )

        val dynamicDaysLabels = arrayOf(
            binding.dayL,
            binding.dayM,
            binding.dayMi,
            binding.dayJ,
            binding.dayV,
            binding.dayS,
            binding.dayD,
        )

        addActionButtonsListener()
        addSwitchListener()
        addCustomCarouselListener(dynamicFrequencyDaysCheckBoxes, dynamicDaysLabels)
        addCheckboxesListener(dynamicFrequencyDaysCheckBoxes)
    }

    private fun addActionButtonsListener(){
        binding.personalizarAlarmaGuardar.setOnClickListener {
            val intent = Intent(application.baseContext, AlarmsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        binding.personalizarAlarmaCancelar.setOnClickListener { finish() }
    }
    private fun addSwitchListener() {
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
    }
    private fun addCustomCarouselListener(
        dynamicFrequencyDaysCheckBoxes: Array<CheckBox>,
        dynamicDaysLabels: Array<TextView>
    ) {
        var currentIndex = 0

        binding.leftButtonCarouselFrequency.setOnClickListener {
            changeVisibilityOfComponents((dynamicDaysLabels + dynamicFrequencyDaysCheckBoxes) as Array<View>, INVISIBLE)

            currentIndex -= 1
            currentIndex = currentIndex.rem(frequencyOptionsLabels.size)

            if (currentIndex < 0) {
                currentIndex += frequencyOptionsLabels.size
            }

            binding.frequencyOption.text = frequencyOptionsLabels[currentIndex]

            if (currentIndex == frequencyOptionsLabels.size - 1) {
                changeVisibilityOfComponents((dynamicDaysLabels + dynamicFrequencyDaysCheckBoxes) as Array<View>, VISIBLE)
            }
        }

        binding.rightButtonCarouselFrequency.setOnClickListener {
            changeVisibilityOfComponents((dynamicDaysLabels + dynamicFrequencyDaysCheckBoxes) as Array<View>, INVISIBLE)

            currentIndex += 1
            currentIndex = currentIndex.rem(frequencyOptionsLabels.size)

            binding.frequencyOption.text = frequencyOptionsLabels[currentIndex]

            if (currentIndex == frequencyOptionsLabels.size - 1) {
                changeVisibilityOfComponents((dynamicDaysLabels + dynamicFrequencyDaysCheckBoxes) as Array<View>, VISIBLE)
            }
        }
    }

    private fun addCheckboxesListener(dynamicFrequencyDaysCheckBoxes: Array<CheckBox>) {

        dynamicFrequencyDaysCheckBoxes.forEach {checkbox ->
            val defaultButtonBackground = checkbox.background
            val defaultButtonTintList = getColorStateList(R.color.azul_oscuro_app)

            checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    buttonView.background = getDrawable(R.color.blanco_app)
                    buttonView.buttonTintList = getColorStateList(R.color.azul_primario_app)
                } else {
                    buttonView.background = defaultButtonBackground
                    buttonView.buttonTintList = defaultButtonTintList
                }
            }
        }
    }

    private fun changeVisibilityOfComponents(componentsArray: Array<View>, visibilityStatus: Int){
        componentsArray.forEach {
            it.visibility = visibilityStatus
        }
    }
}