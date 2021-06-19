package cn.junmov.mirror.asset.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.data.db.entity.Asset

class AssetListAdapter(
    private val click: (View, Asset) -> Unit
) : ListAdapter<Asset, AssetListViewHolder>(DIFF_CALL_BACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetListViewHolder {
        return AssetListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AssetListViewHolder, position: Int) {
        val data = getItem(position)
        with(holder) {
            bindData(data)
            itemView.setOnClickListener { v -> click(v, data) }
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