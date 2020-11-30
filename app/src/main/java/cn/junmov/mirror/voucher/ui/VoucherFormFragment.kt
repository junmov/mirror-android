package cn.junmov.mirror.voucher.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.entity.Account
import cn.junmov.mirror.core.data.entity.Thing
import cn.junmov.mirror.core.utility.onClickItem
import cn.junmov.mirror.core.utility.setupNavigateUp
import cn.junmov.mirror.core.utility.setupSnackBar
import cn.junmov.mirror.databinding.FragmentVoucherFormBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VoucherFormFragment : Fragment() {

    private val viewModel: VoucherFormViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentVoucherFormBinding.inflate(inflater, container, false)
        val thingAdapter: ArrayAdapter<Thing> =
            ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item)
        val accountAdapter: ArrayAdapter<Account> =
            ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item)
        val adapter = SplitEditAdapter(viewModel)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@VoucherFormFragment
            spinnerSplitAccount.setAdapter(accountAdapter)
            spinnerSplitAccount.onClickItem { viewModel.selectAccount(it as Account) }
            spinnerVoucherEvent.setAdapter(thingAdapter)
            spinnerVoucherEvent.onClickItem { viewModel.selectThing(it as Thing) }
            listVoucherSplit.adapter = adapter
        }
        viewModel.things.observe(viewLifecycleOwner) { thingAdapter.addAll(it) }
        viewModel.accounts.observe(viewLifecycleOwner) { accountAdapter.addAll(it) }
        viewModel.inputSplits.observe(viewLifecycleOwner) { adapter.submitList(it) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setupNavigateUp(viewLifecycleOwner, viewModel.updated)
        view.setupSnackBar(viewLifecycleOwner, viewModel.message, Snackbar.LENGTH_SHORT)
    }

}