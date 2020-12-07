package cn.junmov.mirror.debt.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.data.entity.Debt
import cn.junmov.mirror.core.widget.AbstractListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DebtFragment : AbstractListFragment<Debt>() {

    private val viewModel: DebtViewModel by viewModels()

    override fun adapter(): ListAdapter<Debt, *> = DebtListAdapter()

    override fun data(): LiveData<List<Debt>> = viewModel.debts
}