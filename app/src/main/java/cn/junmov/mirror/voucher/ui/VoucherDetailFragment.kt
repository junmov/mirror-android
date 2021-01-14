package cn.junmov.mirror.voucher.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import cn.junmov.mirror.MainNavDirections
import cn.junmov.mirror.R
import cn.junmov.mirror.core.utility.navTo
import cn.junmov.mirror.core.utility.setupNavigateUp
import cn.junmov.mirror.core.utility.setupSnackBar
import cn.junmov.mirror.databinding.FragmentVoucherDetailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VoucherDetailFragment : Fragment() {

    private val viewModel: VoucherDetailViewModel by viewModels()

    private val args: VoucherDetailFragmentArgs by navArgs()

    private val adapter = SplitListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentVoucherDetailBinding.inflate(inflater, container, false)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@VoucherDetailFragment
            listSplit.adapter = adapter
            btnEditVoucherSplits.navTo(
                VoucherDetailFragmentDirections.actionVoucherDetailFragmentToSplitFormFragment(args.voucherId)
            )
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadData(args.voucherId)
        view.setupSnackBar(viewLifecycleOwner, viewModel.message, Snackbar.LENGTH_SHORT)
        view.setupNavigateUp(viewLifecycleOwner, viewModel.updated)
        viewModel.splits.observe(viewLifecycleOwner) { adapter.submitList(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_voucher_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_edit_voucher -> navTo(MainNavDirections.actionGlobalVoucherFormFragment(args.voucherId))
            R.id.option_remove_voucher -> {
                showRemoveDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showRemoveDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("删除交易")
            .setMessage("删除后不可恢复,继续吗？")
            .setPositiveButton("继续") { _, _ -> viewModel.remove() }
            .setNegativeButton("取消", null)
            .show()
    }

}