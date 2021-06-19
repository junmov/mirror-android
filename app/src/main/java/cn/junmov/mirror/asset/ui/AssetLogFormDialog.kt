package cn.junmov.mirror.asset.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.db.entity.Asset
import cn.junmov.mirror.core.utility.onClickItem
import cn.junmov.mirror.core.utility.setupDismiss
import cn.junmov.mirror.core.widget.DatePickerFragment
import cn.junmov.mirror.core.widget.FullScreenDialog
import cn.junmov.mirror.databinding.DialogAssetLogFormBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class AssetLogFormDialog : FullScreenDialog() {

    private val viewModel: AssetLogFormViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DialogAssetLogFormBinding.inflate(inflater, container, false)
        val adapter = ArrayAdapter<Asset>(
            requireContext(), R.layout.support_simple_spinner_dropdown_item
        )
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@AssetLogFormDialog
            ilAssetLogDateAt.setStartIconOnClickListener {
                val picker = DatePickerFragment(LocalDate.now()) { viewModel.setDateAt(it) }
                picker.show(parentFragmentManager, DatePickerFragment.TAG)
            }
            spinnerAssetLogTarget.setAdapter(adapter)
            spinnerAssetLogTarget.onClickItem { viewModel.selectAsset(it as Asset) }
        }
        viewModel.assets.observe(viewLifecycleOwner) { adapter.addAll(it) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDismiss(viewLifecycleOwner, viewModel.updated)
    }

}