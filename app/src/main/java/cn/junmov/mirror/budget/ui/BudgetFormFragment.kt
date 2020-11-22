package cn.junmov.mirror.budget.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import cn.junmov.mirror.core.utility.setupNavigateUp
import cn.junmov.mirror.databinding.FragmentBudgetFormBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BudgetFormFragment : Fragment() {

    private val viewModel: BudgetFormViewModel by viewModels()

    private val args: BudgetFormFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBudgetFormBinding.inflate(inflater, container, false)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@BudgetFormFragment
        }
        viewModel.loadData(args.budgetId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setupNavigateUp(viewLifecycleOwner, viewModel.updated)
    }

}