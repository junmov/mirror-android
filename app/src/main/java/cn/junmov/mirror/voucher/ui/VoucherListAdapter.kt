package cn.junmov.mirror.voucher.ui

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.db.view.ItemVoucher
import cn.junmov.mirror.core.widget.FourCellListItemHolder
import cn.junmov.mirror.voucher.data.ItemVoucherDiffCallBack
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class VoucherListAdapter(
    private val onClickCopy: (Long) -> Unit,
    private val onClickRemove: (Long) -> Unit
) :
    ListAdapter<ItemVoucher, FourCellListItemHolder>(ItemVoucherDiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FourCellListItemHolder {
        return FourCellListItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FourCellListItemHolder, position: Int) {
        val data = getItem(position)
        holder.bindUiModel(data)
        holder.itemView.setOnClickListener { showPop(it, data) }
    }

    private fun showPop(v: View, item: ItemVoucher) {
        val pop = PopupMenu(v.context, v)
        pop.menuInflater.inflate(R.menu.pop_voucher, pop.menu)
        pop.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener when (it.itemId) {
                R.id.pop_voucher_copy -> {
                    onClickCopy(item.id)
                    true
                }
                R.id.pop_voucher_remove -> {
                    showRemoveDialog(v, item.id)
                    true
                }
                else -> false
            }
        }
        pop.show()
    }

    private fun showRemoveDialog(v: View, id: Long) {
        MaterialAlertDialogBuilder(v.context)
            .setTitle("删除交易")
            .setMessage("删除后不可恢复,继续吗？")
            .setPositiveButton("继续") { _, _ ->
                onClickRemove(id)
            }
            .setNegativeButton("取消", null)
            .show()
    }
}