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

    override fun adapter(): ListAdapter<DateRepay, *> = DateRepayListAdapter()

    override fun data(): LiveData<List<DateRepay>> = viewModel.dateRepays
}