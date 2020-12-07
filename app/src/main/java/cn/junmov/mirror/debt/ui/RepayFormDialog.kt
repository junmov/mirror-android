package cn.junmov.mirror.debt.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import cn.junmov.mirror.core.utility.setupDismiss
import cn.junmov.mirror.core.widget.FullScreenDialog
import cn.junmov.mirror.databinding.DialogRepayFormBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepayFormDialog : FullScreenDialog() {

    private val viewModel: RepayFormViewModel by viewModels()

    private val args: RepayFormDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DialogRepayFormBinding.inflate(inflater, container, false)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@RepayFormDialog
        }
        viewModel.loadData(args.repayId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupDismiss(viewLifecycleOwner, viewModel.updated)
    }

}