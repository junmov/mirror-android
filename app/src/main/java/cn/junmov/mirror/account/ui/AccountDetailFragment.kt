package cn.junmov.mirror.account.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cn.junmov.mirror.R
import cn.junmov.mirror.core.utility.setupSnackBar
import cn.junmov.mirror.databinding.FragmentAccountDetailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountDetailFragment : Fragment() {

    private val viewModel: AccountDetailViewModel by viewModels()

    private val args: AccountDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAccountDetailBinding.inflate(inflater, container, false)
        val adapter = ChildAccountListAdapter { id, title ->
            findNavController().navigate(
                AccountDetailFragmentDirections.actionAccountDetailFragmentToAccountTradingFragment(
                    accountId = id, title = title
                )
            )
        }
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@AccountDetailFragment
            btnAdjustBalance.setOnClickListener { showAdjustDialog(it) }
            listAccountChildren.adapter = adapter
        }
        viewModel.loadData(args.accountId)
        viewModel.children.observe(viewLifecycleOwner) { adapter.submitList(it) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setupSnackBar(viewLifecycleOwner, viewModel.message, Snackbar.LENGTH_SHORT)
    }

    private fun showAdjustDialog(view: View) {
        MaterialAlertDialogBuilder(view.context)
            .setTitle("调整余额")
            .setView(R.layout.dialog_single_edit)
            .setPositiveButton("调整") { dialog, _ ->
                val input = (dialog as AlertDialog).findViewById<TextView>(R.id.dialog_single_text)
                viewModel.adjustBalance(input?.text.toString())
            }
            .setNegativeButton("取消", null)
            .show()
    }

}