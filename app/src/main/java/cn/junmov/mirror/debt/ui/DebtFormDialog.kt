package cn.junmov.mirror.debt.ui

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
import cn.junmov.mirror.core.widget.FullScreenDialog
import cn.junmov.mirror.databinding.DialogDebtFormBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DebtFormDialog : FullScreenDialog() {

    private val viewModel: DebtFormViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DialogDebtFormBinding.inflate(inflater, container, false)
        val adapter =
            ArrayAdapter<Account>(requireContext(), R.layout.support_simple_spinner_dropdown_item)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@DebtFormDialog
            spinnerDebtAccount.setAdapter(adapter)
            spinnerDebtAccount.onClickItem { viewModel.selectAccount(it as Account) }
        }
        viewModel.accounts.observe(viewLifecycleOwner) { adapter.addAll(it) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupDismiss(viewLifecycleOwner, viewModel.updated)
    }

}