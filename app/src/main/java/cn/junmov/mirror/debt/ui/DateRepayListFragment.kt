package cn.junmov.mirror.debt.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.core.widget.AbstractListFragment
import cn.junmov.mirror.debt.data.DateRepay
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DateRepayListFragment : AbstractListFragment<DateRepay>() {

    private val viewModel: DateRepayListViewModel by viewModels()

    override fun adapter(): ListAdapter<DateRepay, *> = DateRepayListAdapter { r, v ->
        v.findNavController().navigate(
            DateRepayListFragmentDirections.actionDateRepayListFragmentToScreenRepayDetail(
                TimeUtils.dateToString(r.dateAt)
            )
        )
    }

    override fun data(): LiveData<List<DateRepay>> = viewModel.dateRepays

}