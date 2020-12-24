package cn.junmov.mirror.voucher.ui

import androidx.recyclerview.widget.DiffUtil
import cn.junmov.mirror.core.adapter.AbstractSingleLineListAdapter
import cn.junmov.mirror.core.adapter.SingleLineModel
import cn.junmov.mirror.core.data.db.entity.Split

class SplitListAdapter : AbstractSingleLineListAdapter<Split>(DIFF_CALL_BACK) {

    override fun trans(data: Split): SingleLineModel.UiData = data.singleLineData()

    companion object {
        private val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<Split>() {
            override fun areItemsTheSame(oldItem: Split, newItem: Split): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Split, newItem: Split): Boolean {
                return oldItem == newItem
            }
        }
    }


}