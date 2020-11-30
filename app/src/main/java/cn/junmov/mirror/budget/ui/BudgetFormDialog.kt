package cn.junmov.mirror.budget.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import cn.junmov.mirror.core.utility.setupNavigateUp
import cn.junmov.mirror.core.widget.FullScreenDialog
import cn.junmov.mirror.databinding.DialogBudgetFormBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BudgetFormDialog : FullScreenDialog() {

    private val viewModel: BudgetFormViewModel by viewModels()

    private val args: BudgetFormDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DialogBudgetFormBinding.inflate(inflater, container, false)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@BudgetFormDialog
        }
        viewModel.loadData(args.budgetId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setupNavigateUp(viewLifecycleOwner, viewModel.updated)
    }

}