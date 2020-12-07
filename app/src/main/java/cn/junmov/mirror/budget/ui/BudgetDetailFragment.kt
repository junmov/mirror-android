package cn.junmov.mirror.budget.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import cn.junmov.mirror.R
import cn.junmov.mirror.databinding.FragmentBudgetDetailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BudgetDetailFragment : Fragment() {

    private val viewModel: BudgetDeltaViewModel by viewModels()

    private val args: BudgetDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBudgetDetailBinding.inflate(inflater, container, false)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@BudgetDetailFragment
            btnSetupBudget.setOnClickListener { showDialog() }
        }
        viewModel.loadData(args.categoryId)
        return binding.root
    }

    private fun showDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("设置预算")
            .setView(R.layout.dialog_single_edit)
            .setPositiveButton("设置") { dialog, _ ->
                val view = (dialog as AlertDialog).findViewById<TextView>(R.id.dialog_single_text)
                viewModel.setupBudget(view?.text.toString())
            }
            .setNegativeButton("取消", null)
            .show()
    }

}