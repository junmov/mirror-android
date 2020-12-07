package cn.junmov.mirror.asset.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.data.entity.Asset
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.navTo
import cn.junmov.mirror.core.widget.TwoLineListItemViewHolder

class AssetListAdapter : ListAdapter<Asset, TwoLineListItemViewHolder>(DIFF_CALL_BACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwoLineListItemViewHolder {
        return TwoLineListItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TwoLineListItemViewHolder, position: Int) {
        val data = getItem(position)
        with(holder) {
            bind(
                data.name,
                "投入：${MoneyUtils.centToYuan(data.buy)} 回报：${MoneyUtils.centToYuan(data.sell)}",
                MoneyUtils.centToYuan(data.count)
            )
            itemView.navTo(
                AssetFragmentDirections.actionAssetFragmentToAssetDetailFragment(data.id)
            )
        }
    }

    companion object {
        private val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<Asset>() {
            override fun areItemsTheSame(oldItem: Asset, newItem: Asset): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Asset, newItem: Asset): Boolean {
                return oldItem == newItem
            }

        }
    }
}