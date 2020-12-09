package cn.junmov.mirror.budget.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import cn.junmov.mirror.core.utility.setupInputDialog
import cn.junmov.mirror.databinding.FragmentBudgetDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BudgetDetailFragment : Fragment() {

    private val viewModel: BudgetDetailViewModel by viewModels()

    private val args: BudgetDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBudgetDetailBinding.inflate(inflater, container, false)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@BudgetDetailFragment
            btnAdjustBudget.setupInputDialog("调整预算", "调整") { viewModel.submitBudget(it) }
        }
        viewModel.loadData(args.categoryId)
        return binding.root
    }

}