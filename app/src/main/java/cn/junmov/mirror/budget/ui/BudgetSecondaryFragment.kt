package cn.junmov.mirror.budget.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import cn.junmov.mirror.databinding.FragmentBudgetSecondaryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BudgetSecondaryFragment : Fragment() {

    private val viewModel: BudgetSecondaryViewModel by viewModels()

    private val args: BudgetSecondaryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBudgetSecondaryBinding.inflate(inflater, container, false)
        val adapter = BudgetListAdapter { view, second ->
            view.findNavController().navigate(
                BudgetSecondaryFragmentDirections.actionBudgetSecondaryFragmentToBudgetDeltaFragment(
                   categoryId =  second.id, title = second.name
                )
            )
        }
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@BudgetSecondaryFragment
            listSecondaryBudget.adapter = adapter
        }
        viewModel.loadData(args.budgetId)
        viewModel.secondaryBudgets.observe(viewLifecycleOwner) { adapter.submitList(it) }
        return binding.root
    }

}