package cn.junmov.mirror.asset.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.asset.data.AssetLogDiffCallBack
import cn.junmov.mirror.core.data.db.entity.AssetLog

class AssetLogListAdapter(
    private val callback: (View, AssetLog) -> Unit
) : ListAdapter<AssetLog, AssetLogViewHolder>(AssetLogDiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetLogViewHolder {
        return AssetLogViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AssetLogViewHolder, position: Int) {
        val data = getItem(position)
        with(holder) {
            bindData(data)
            itemView.setOnClickListener { callback(it, data) }
        }
    }
}