package cn.junmov.mirror.debt.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import cn.junmov.mirror.core.utility.navTo
import cn.junmov.mirror.databinding.FragmentDebtDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DebtDetailFragment : Fragment() {

    private val args: DebtDetailFragmentArgs by navArgs()

    private val viewModel: DebtDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDebtDetailBinding.inflate(inflater, container, false)
        val adapter = RepayListAdapter()
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@DebtDetailFragment
            listAgingDebtItem.adapter = adapter
            btnStopLoss.navTo(
                DebtDetailFragmentDirections.actionDebtDetailFragmentToStopLossDialog(args.debtId)
            )
        }
        viewModel.items.observe(viewLifecycleOwner) { adapter.submitList(it) }
        viewModel.loadData(args.debtId)
        return binding.root
    }

}