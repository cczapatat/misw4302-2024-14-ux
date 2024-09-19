package com.uniandes.edu.uxalarm

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.uniandes.edu.uxalarm.databinding.FragmentAlarmItemBinding

class AlarmItem : Fragment() {

    companion object {
        fun newInstance(images: IntArray): AlarmItem {
            val fragment = AlarmItem()
            val args = Bundle()
            args.putIntArray("images", images)
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentAlarmItemBinding? = null
    private val binding get() = _binding!!
    private var images = intArrayOf()
    private var currentIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlarmItemBinding.inflate(inflater, container, false)
        arguments?.getIntArray("images")?.let {
            images = it
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.leftButton.setOnClickListener {
            currentIndex -= 1

            if (currentIndex < 0) {
                currentIndex = images.size - 1
                animateImageChange()
            } else if (currentIndex < images.size) {
                animateImageChange()
            } else {
                currentIndex = 0
                animateImageChange()
            }
        }
        binding.rightButton.setOnClickListener {
            currentIndex += 1

            if (currentIndex >= images.size) {
                currentIndex = 0
                animateImageChange()
            } else if (currentIndex > 0) {
                animateImageChange()
            }
        }
    }

    fun updateImage() {
        binding.imageView.foreground =
            ContextCompat.getDrawable(binding.root.context, images[currentIndex])
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}