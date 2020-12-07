package cn.junmov.mirror.voucher.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.entity.Account
import cn.junmov.mirror.core.utility.onClickItem
import cn.junmov.mirror.databinding.FragmentSplitFormBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplitFormFragment : Fragment() {

    private val viewModel: SplitFormViewModel by viewModels()

    private val args: SplitFormFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSplitFormBinding.inflate(inflater, container, false)
        val accountAdapter: ArrayAdapter<Account> =
            ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item)
        val adapter = SplitEditAdapter(viewModel)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@SplitFormFragment
            spinnerSplitAccount.setAdapter(accountAdapter)
            spinnerSplitAccount.onClickItem { viewModel.selectAccount(it as Account) }
            listVoucherSplit.adapter = adapter
        }
        viewModel.loadData(args.voucherId)
        viewModel.accounts.observe(viewLifecycleOwner) { accountAdapter.addAll(it) }
        viewModel.splits.observe(viewLifecycleOwner) { adapter.submitList(it) }
        return binding.root
    }

}