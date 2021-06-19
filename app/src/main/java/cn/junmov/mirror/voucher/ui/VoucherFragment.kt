package cn.junmov.mirror.voucher.ui

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.utility.*
import cn.junmov.mirror.core.widget.DatePickerFragment
import cn.junmov.mirror.core.widget.FullScreenDialog
import cn.junmov.mirror.core.widget.TimePickerFragment
import cn.junmov.mirror.databinding.FragmentVoucherBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalTime

@AndroidEntryPoint
class VoucherFragment : FullScreenDialog() {

    private val viewModel: VoucherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentVoucherBinding.inflate(inflater, container, false)
        val thingAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(), R.layout.support_simple_spinner_dropdown_item, Things.values()
        )
        val debitAdapter: ArrayAdapter<Account> =
            ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item)
        val creditAdapter: ArrayAdapter<Account> =
            ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@VoucherFragment
            spinnerThing.setAdapter(thingAdapter)
            spinnerThing.onClickItem { viewModel.selectThing(it as String) }
            spinnerCreditAccount.setAdapter(creditAdapter)
            spinnerCreditAccount.onClickItem { viewModel.selectCredit(it as Account) }
            spinnerDebitAccount.setAdapter(debitAdapter)
            spinnerDebitAccount.onClickItem { viewModel.selectDebit(it as Account) }
            voucherDateAt.setOnClickListener {
                val fragment =
                    DatePickerFragment(LocalDate.now()) { dateAt -> viewModel.selectDate(dateAt) }
                fragment.show(parentFragmentManager, DatePickerFragment.TAG)
            }
            voucherTimeAt.setOnClickListener {
                val fragment =
                    TimePickerFragment(LocalTime.now()) { timeAt -> viewModel.selectTime(timeAt) }
                fragment.show(parentFragmentManager, TimePickerFragment.TAG)
            }
        }
        viewModel.accounts.observe(viewLifecycleOwner) {
            debitAdapter.addAll(it)
            creditAdapter.addAll(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupDismiss(viewLifecycleOwner, viewModel.updated)
    }


}