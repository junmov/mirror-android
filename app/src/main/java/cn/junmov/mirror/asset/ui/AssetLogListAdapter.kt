package cn.junmov.mirror.asset.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.data.entity.AssetLog
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.core.widget.TwoLineListItemViewHolder

class AssetLogListAdapter : ListAdapter<AssetLog, TwoLineListItemViewHolder>(DIFF_CALL_BACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwoLineListItemViewHolder {
        return TwoLineListItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TwoLineListItemViewHolder, position: Int) {
        val data = getItem(position)
        val primary = if (data.buy) {
            "买入 ${MoneyUtils.centToYuan(data.count)}份"
        } else {
            "卖出 ${MoneyUtils.centToYuan(data.count)}份"
        }
        with(holder) {
            bind(
                primary,
                TimeUtils.dateTimeToString(data.createAt),
                MoneyUtils.centToYuan(data.amount)
            )
        }
    }

    companion object {
        private val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<AssetLog>() {
            override fun areItemsTheSame(oldItem: AssetLog, newItem: AssetLog): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AssetLog, newItem: AssetLog): Boolean {
                return oldItem == newItem
            }
        }
    }
}
