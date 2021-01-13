package cn.junmov.mirror.debt.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.core.widget.AbstractListFragment
import cn.junmov.mirror.debt.data.DateRepay
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class BillFragment : AbstractListFragment<DateRepay>() {

    private val viewModel: BillViewModel by viewModels()

    override fun adapter(): ListAdapter<DateRepay, *> {
        return DateRepayListAdapter { dateAt -> showDialog(dateAt) }
    }

    private fun showDialog(dateAt: LocalDate) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("还清本期")
            .setMessage(TimeUtils.dateToString(dateAt))
            .setPositiveButton("还款") { _, _ ->
                viewModel.submitSettled(dateAt)
            }
            .setNegativeButton("取消", null)
            .show()
    }

    override fun data(): LiveData<List<DateRepay>> = viewModel.dateRepays
}