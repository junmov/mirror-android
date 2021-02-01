package cn.junmov.mirror.debt.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import cn.junmov.mirror.R
import cn.junmov.mirror.databinding.FragmentRepayDetailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepayDetailFragment : Fragment() {

    private val viewModel: RepayDetailViewModel by viewModels()

    private val args: RepayDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRepayDetailBinding.inflate(inflater, container, false)
        val adapter = RepayListAdapter { _, _ -> }
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@RepayDetailFragment
            listRepay.adapter = adapter
        }
        setHasOptionsMenu(true)
        viewModel.loadData(args.dateAt)
        viewModel.repays.observe(viewLifecycleOwner) { adapter.submitList(it) }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_repay_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_repay -> {
                showDialog(args.dateAt)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDialog(dateAt: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("还清本期")
            .setMessage(dateAt)
            .setPositiveButton("还款") { _, _ ->
                viewModel.submitSettled(dateAt)
            }
            .setNegativeButton("取消", null)
            .show()
    }
}