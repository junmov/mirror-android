package cn.junmov.mirror.debt.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import cn.junmov.mirror.core.utility.setupNavigateUp
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
        val adapter = RepayListAdapter { _, _ -> }
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@DebtDetailFragment
            listAgingDebtItem.adapter = adapter
        }
        viewModel.repays.observe(viewLifecycleOwner) { adapter.submitList(it) }
        viewModel.loadData(args.debtId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setupNavigateUp(viewLifecycleOwner, viewModel.updated)
    }

}