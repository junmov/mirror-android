package cn.junmov.mirror.thing.ui

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import cn.junmov.mirror.core.adapter.AbstractSingleLineListAdapter
import cn.junmov.mirror.core.adapter.SingleLineModel
import cn.junmov.mirror.core.data.entity.Thing

class ThingListAdapter : AbstractSingleLineListAdapter<Thing>(diff) {

    override fun trans(data: Thing): SingleLineModel.UiData = data.toSingleLineUiModel()

    override fun click(data: Thing): View.OnClickListener = View.OnClickListener {
        it.findNavController().navigate(
            ThingFragmentDirections.actionThingFragmentToThingDetailFragment(data.id, data.name)
        )
    }

    companion object {
        private val diff = object : DiffUtil.ItemCallback<Thing>() {
            override fun areItemsTheSame(oldItem: Thing, newItem: Thing): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Thing, newItem: Thing): Boolean {
                return oldItem == newItem
            }
        }
    }
}