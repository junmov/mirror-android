package cn.junmov.mirror.budget.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cn.junmov.mirror.databinding.FragmentBudgetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BudgetFragment : Fragment() {

    private val viewModel: BudgetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBudgetBinding.inflate(inflater, container, false)
        val adapter = BudgetListAdapter()
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@BudgetFragment
            listBudget.adapter = adapter
        }
        viewModel.loadData()
        viewModel.budgets.observe(viewLifecycleOwner) { adapter.submitList(it) }
        return binding.root
    }
}