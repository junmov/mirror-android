package cn.junmov.mirror.wallet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.AccountType
import cn.junmov.mirror.core.utility.onClickItem
import cn.junmov.mirror.core.utility.setupDismiss
import cn.junmov.mirror.core.widget.FullScreenDialog
import cn.junmov.mirror.databinding.DialogWalletFormBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalletFormDialog : FullScreenDialog() {

    private val viewModel: WalletFormViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = DialogWalletFormBinding.inflate(inflater, container, false)
        val typeAdapter = ArrayAdapter(
            requireContext(), R.layout.support_simple_spinner_dropdown_item,
            AccountType.wallets
        )
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@WalletFormDialog
            spinnerWalletType.setAdapter(typeAdapter)
            spinnerWalletType.onClickItem { viewModel.selectType(it as AccountType) }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupDismiss(viewLifecycleOwner, viewModel.updated)
    }

}