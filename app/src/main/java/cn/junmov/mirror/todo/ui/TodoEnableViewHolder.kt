package cn.junmov.mirror.todo.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.junmov.mirror.R
import com.google.android.material.switchmaterial.SwitchMaterial

class TodoEnableViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val summary: TextView = itemView.findViewById(R.id.tv_schedule_summary)
    val nextRun: TextView = itemView.findViewById(R.id.tv_schedule_next_run)
    val count: TextView = itemView.findViewById(R.id.tv_schedule_count)
    val enable: SwitchMaterial = itemView.findViewById(R.id.sw_schedule_enable)

}