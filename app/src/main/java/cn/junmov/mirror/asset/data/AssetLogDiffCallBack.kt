package cn.junmov.mirror.asset.data

import androidx.recyclerview.widget.DiffUtil
import cn.junmov.mirror.core.data.db.entity.AssetLog

object AssetLogDiffCallBack : DiffUtil.ItemCallback<AssetLog>() {
    override fun areItemsTheSame(oldItem: AssetLog, newItem: AssetLog): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AssetLog, newItem: AssetLog): Boolean {
        return oldItem == newItem
    }
}