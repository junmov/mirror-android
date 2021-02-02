package cn.junmov.mirror.budget.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import cn.junmov.mirror.R
import cn.junmov.mirror.core.utility.navTo
import cn.junmov.mirror.databinding.FragmentBudgetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BudgetFragment : Fragment() {

    private val viewModel: BudgetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBudgetBinding.inflate(inflater, container, false)
        val adapter = BudgetListAdapter { view, first ->
            view.findNavController().navigate(
                BudgetFragmentDirections.actionPageBudgetToBudgetSecondaryFragment(
                    budgetId = first.id, title = first.name
                )
            )
        }
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@BudgetFragment
            listBudget.adapter = adapter
        }
        viewModel.loadData()
        viewModel.budgets.observe(viewLifecycleOwner) { adapter.submitList(it) }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_create, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_create -> navTo(BudgetFragmentDirections.actionPageBudgetToBudgetFormDialog())
            else -> super.onOptionsItemSelected(item)
        }
    }
}