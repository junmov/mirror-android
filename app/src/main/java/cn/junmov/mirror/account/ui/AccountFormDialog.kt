package cn.junmov.mirror.account.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.AccountType
import cn.junmov.mirror.core.data.entity.Account
import cn.junmov.mirror.core.utility.onClickItem
import cn.junmov.mirror.core.utility.setupDismiss
import cn.junmov.mirror.core.widget.FullScreenDialog
import cn.junmov.mirror.databinding.DialogAccountFormBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFormDialog : FullScreenDialog() {

    private val viewModel: AccountFormViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = DialogAccountFormBinding.inflate(inflater, container, false)
        val typeAdapter = ArrayAdapter(
            requireContext(), R.layout.support_simple_spinner_dropdown_item,
            AccountType.values()
        )
        val accountAdapter = ArrayAdapter<Account>(
            requireContext(), R.layout.support_simple_spinner_dropdown_item,
        )
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@AccountFormDialog
            spinnerAccountParent.setAdapter(accountAdapter)
            spinnerAccountParent.onClickItem { viewModel.selectParent(it as Account) }
            spinnerAccountType.setAdapter(typeAdapter)
            spinnerAccountType.onClickItem { viewModel.selectType(it as AccountType) }
        }
        viewModel.parentAccounts.observe(viewLifecycleOwner) {
            accountAdapter.addAll(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupDismiss(viewLifecycleOwner, viewModel.updated)
    }

}