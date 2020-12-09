package cn.junmov.mirror.debt.ui

import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import cn.junmov.mirror.core.adapter.SingleLineModel
import cn.junmov.mirror.core.adapter.SingleLinePagingAdapter
import cn.junmov.mirror.core.widget.PagedListFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class BillFragment : PagedListFragment<SingleLineModel>() {

    private val viewModel: BillViewModel by viewModels()

    override fun adapter(): PagingDataAdapter<SingleLineModel, *> {
        return SingleLinePagingAdapter { id, title ->
            showDialog(id, title)
        }
    }

    override fun pagedData(): Flow<PagingData<SingleLineModel>> = viewModel.bills

    private fun showDialog(billId: Long, title: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("是否结清此账单")
            .setMessage(title)
            .setPositiveButton("是的") { _, _ ->
                viewModel.submitSettled(billId)
            }
            .setNegativeButton("取消", null)
            .show()
    }
}