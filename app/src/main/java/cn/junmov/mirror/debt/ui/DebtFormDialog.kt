package cn.junmov.mirror.debt.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import cn.junmov.mirror.core.utility.setupDismiss
import cn.junmov.mirror.core.widget.DatePickerFragment
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
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@DebtFormDialog
            ilDebtStartAt.setStartIconOnClickListener {
                val fragment = DatePickerFragment { date ->
                    viewModel.setStartDateAt(date)
                }
                fragment.show(parentFragmentManager, DatePickerFragment.TAG)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupDismiss(viewLifecycleOwner, viewModel.updated)
    }

}