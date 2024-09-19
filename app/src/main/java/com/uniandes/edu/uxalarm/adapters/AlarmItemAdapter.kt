package com.uniandes.edu.uxalarm.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.uniandes.edu.uxalarm.AlarmItem
import com.uniandes.edu.uxalarm.EditAlarmActivity
import com.uniandes.edu.uxalarm.R

class AlarmItemAdapter(
    private val fragmentManager: FragmentManager,
    private val itemCount: Int,
    private val imagesList: List<IntArray>
) : RecyclerView.Adapter<AlarmItemAdapter.AlarmItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_alarm_item, parent, false)
        return AlarmItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlarmItemViewHolder, position: Int) {
        val fragment = AlarmItem.newInstance(imagesList[position])
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(holder.container.id, fragment)
        fragmentTransaction.commit()

        holder.imageView.foreground =
            ContextCompat.getDrawable(holder.itemView.context, imagesList[position][0])

        holder.btnEdit.setOnClickListener {
            val context = holder.itemView.context
            context.startActivity(Intent(context, EditAlarmActivity::class.java))
        }
    }

    override fun getItemCount() = itemCount

    class AlarmItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container: FrameLayout = itemView.findViewById(R.id.fragmentAlarmItem)
        val btnEdit: FloatingActionButton = itemView.findViewById(R.id.btnEdit)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}