package cn.junmov.mirror.voucher.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.db.entity.Thing
import cn.junmov.mirror.core.utility.onClickItem
import cn.junmov.mirror.core.utility.setupNavigateUp
import cn.junmov.mirror.databinding.FragmentVoucherFormBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VoucherFormFragment : Fragment() {

    private val viewModel: VoucherFormViewModel by viewModels()

    private val args: VoucherFormFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentVoucherFormBinding.inflate(inflater, container, false)
        val thingAdapter: ArrayAdapter<Thing> =
            ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@VoucherFormFragment
            spinnerVoucherThing.setAdapter(thingAdapter)
            spinnerVoucherThing.onClickItem { viewModel.selectThing(it as Thing) }
        }
        viewModel.loadData(args.voucherId)
        viewModel.things.observe(viewLifecycleOwner) { thingAdapter.addAll(it) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setupNavigateUp(viewLifecycleOwner, viewModel.updated)
    }

}