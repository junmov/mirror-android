package cn.junmov.mirror.debt.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import cn.junmov.mirror.R
import cn.junmov.mirror.core.utility.navTo
import cn.junmov.mirror.core.utility.setupNavigateUp
import cn.junmov.mirror.databinding.FragmentDebtDetailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        val adapter = RepayListAdapter { view, repay ->
            if (!repay.settled) {
                view.findNavController().navigate(
                    DebtDetailFragmentDirections.actionDebtDetailFragmentToRepayFormDialog(repay.id)
                )
            }
        }
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@DebtDetailFragment
            listAgingDebtItem.adapter = adapter
            btnStopLoss.navTo(
                DebtDetailFragmentDirections.actionDebtDetailFragmentToStopLossDialog(args.debtId)
            )
        }
        viewModel.repays.observe(viewLifecycleOwner) { adapter.submitList(it) }
        viewModel.loadData(args.debtId)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setupNavigateUp(viewLifecycleOwner, viewModel.updated)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_debt_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_remove_debt -> {
                showDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("删除债务")
            .setMessage("删除后无法恢复，确定删除？")
            .setPositiveButton("删除") { _, _ ->
                viewModel.removeDebt()
            }
            .setNegativeButton("取消", null)
            .show()
    }

}