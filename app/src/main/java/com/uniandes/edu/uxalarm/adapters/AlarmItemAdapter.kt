package com.uniandes.edu.uxalarm.adapters

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.uniandes.edu.uxalarm.EditAlarmActivity
import com.uniandes.edu.uxalarm.R
import com.uniandes.edu.uxalarm.databinding.FragmentAlarmItemBinding
import com.uniandes.edu.uxalarm.dtos.AlarmItem

class AlarmItemAdapter(
    private val alarmItems: Array<AlarmItem>,
) : RecyclerView.Adapter<AlarmItemAdapter.AlarmItemViewHolder>() {

    private val currentsIndex = Array(alarmItems.size) { alarmItems[it].clockIndex }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmItemViewHolder {
        val withDataBinding: FragmentAlarmItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), AlarmItemViewHolder.LAYOUT, parent, false
        )

        return AlarmItemViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlarmItemViewHolder, position: Int) {
        holder.viewDataBinding.also {
            updateImage(holder, position)
            holder.viewDataBinding.txtTitle.text = alarmItems[position].title
            holder.viewDataBinding.txtTime.text = alarmItems[position].time
            holder.viewDataBinding.switchDisable.isChecked = alarmItems[position].disable
            holder.viewDataBinding.switchDisable.thumbDrawable = ContextCompat.getDrawable(
                holder.itemView.context,
                if (alarmItems[position].disable) {
                    R.drawable.handle_check
                } else {
                    R.drawable.handle_shape
                }
            )

            holder.viewDataBinding.switchDisable.setOnCheckedChangeListener { _, b ->
                holder.viewDataBinding.switchDisable.thumbDrawable = ContextCompat.getDrawable(
                    holder.itemView.context,
                    if (b) {
                        R.drawable.handle_check
                    } else {
                        R.drawable.handle_shape
                    }
                )
            }

            holder.viewDataBinding.leftButton.setOnClickListener {
                currentsIndex[position] -= 1

                if (currentsIndex[position] < 0) {
                    currentsIndex[position] = alarmItems[position].images.size - 1
                }
                animateImageChange(holder, position)
            }

            holder.viewDataBinding.rightButton.setOnClickListener {
                currentsIndex[position] += 1

                if (currentsIndex[position] >= alarmItems[position].images.size) {
                    currentsIndex[position] = 0
                }
                animateImageChange(holder, position)
            }

            holder.viewDataBinding.btnEdit.setOnClickListener {
                val context = holder.itemView.context
                context.startActivity(Intent(context, EditAlarmActivity::class.java))
            }
        }
    }

    private fun updateImage(holder: AlarmItemViewHolder, position: Int) {
        holder.viewDataBinding.imageView.foreground = ContextCompat.getDrawable(
            holder.itemView.context,
            alarmItems[position].images[currentsIndex[position]]
        )
    }

    private fun animateImageChange(holder: AlarmItemViewHolder, position: Int) {
        val fadeOut =
            ObjectAnimator.ofFloat(holder.viewDataBinding.imageView, "alpha", 1f, 0f).apply {
                duration = 250
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        updateImage(holder, position)
                        ObjectAnimator.ofFloat(holder.viewDataBinding.imageView, "alpha", 0f, 1f)
                            .apply {
                                duration = 250
                            }.start()
                    }
                })
            }
        fadeOut.start()
    }

    override fun getItemCount() = alarmItems.size

    class AlarmItemViewHolder(val viewDataBinding: FragmentAlarmItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.fragment_alarm_item
        }
    }
}