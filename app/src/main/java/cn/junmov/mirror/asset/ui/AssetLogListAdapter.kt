package cn.junmov.mirror.asset.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.data.db.entity.AssetLog
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.core.widget.TwoLineListItemViewHolder

class AssetLogListAdapter(
    private val callback: (AssetLog) -> Unit
) : ListAdapter<AssetLog, TwoLineListItemViewHolder>(DIFF_CALL_BACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwoLineListItemViewHolder {
        return TwoLineListItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TwoLineListItemViewHolder, position: Int) {
        val data = getItem(position)
        val secondary = if (data.success) {
            if (data.buy) {
                "已买入 ${MoneyUtils.centToYuan(data.count)}份"
            } else {
                "已卖出 ${MoneyUtils.centToYuan(data.count)}份"
            }
        } else {
            "交易中"
        }
        with(holder) {
            bind(
                TimeUtils.dateToString(data.dateAt),
                secondary,
                MoneyUtils.centToYuan(data.amount)
            )
            itemView.setOnClickListener { callback(data) }
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
