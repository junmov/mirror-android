package cn.junmov.mirror.wallet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import cn.junmov.mirror.core.utility.navTo
import cn.junmov.mirror.core.utility.setupInputDialog
import cn.junmov.mirror.core.utility.setupSnackBar
import cn.junmov.mirror.databinding.FragmentWalletDetailBinding
import cn.junmov.mirror.voucher.ui.VoucherListAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalletDetailFragment : Fragment() {

    private val viewModel: WalletDetailViewModel by viewModels()

    private val args: WalletDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentWalletDetailBinding.inflate(inflater, container, false)
        val adapter = VoucherListAdapter()

        binding.apply {
            vm = viewModel
            lifecycleOwner = this@WalletDetailFragment
            listWalletLastTrade.adapter = adapter
            btnAdjustWalletBalance.setupInputDialog("调整余额", "调整") {
                viewModel.submitBalance(it)
            }
            linkWalletAllTrade.navTo(
                WalletDetailFragmentDirections.actionAccountDetailFragmentToAccountTradingFragment(
                    accountId = args.accountId, title = args.title
                )
            )
        }
        viewModel.loadData(args.accountId)
        viewModel.vouchers.observe(viewLifecycleOwner) { adapter.submitList(it) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setupSnackBar(viewLifecycleOwner, viewModel.message, Snackbar.LENGTH_SHORT)
    }

}