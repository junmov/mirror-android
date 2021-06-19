package cn.junmov.mirror.account.ui.budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.utility.onClickItem
import cn.junmov.mirror.core.utility.setupDismiss
import cn.junmov.mirror.core.utility.setupNavigateUp
import cn.junmov.mirror.core.widget.FullScreenDialog
import cn.junmov.mirror.databinding.DialogBudgetFormBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BudgetFormDialog : FullScreenDialog() {

    private val viewModel: BudgetFormViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DialogBudgetFormBinding.inflate(inflater, container, false)
        val adapter =
            ArrayAdapter<Account>(requireContext(), R.layout.support_simple_spinner_dropdown_item)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@BudgetFormDialog
            spinnerBudgetAccount.setAdapter(adapter)
            spinnerBudgetAccount.onClickItem { viewModel.selectAccount(it as Account) }
        }
        viewModel.expends.observe(viewLifecycleOwner) { adapter.addAll(it) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDismiss(viewLifecycleOwner, viewModel.updated)
    }

}