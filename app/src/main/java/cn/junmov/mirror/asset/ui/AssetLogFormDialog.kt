package cn.junmov.mirror.asset.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.core.utility.setupDismiss
import cn.junmov.mirror.core.widget.DatePickerFragment
import cn.junmov.mirror.core.widget.FullScreenDialog
import cn.junmov.mirror.databinding.DialogAssetLogFormBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class AssetLogFormDialog : FullScreenDialog() {

    private val viewModel: AssetLogFormViewModel by viewModels()

    private val args: AssetLogFormDialogArgs by navArgs()

    private lateinit var dateAt: LocalDate

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DialogAssetLogFormBinding.inflate(inflater, container, false)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@AssetLogFormDialog
            ilAssetLogDateAt.setStartIconOnClickListener {
                val picker = DatePickerFragment(dateAt) { viewModel.setDateAt(it) }
                picker.show(parentFragmentManager, DatePickerFragment.TAG)
            }
        }
        viewModel.loadData(args.assetId, args.assetLogId)
        viewModel.inputDateAt.observe(viewLifecycleOwner) { dateAt = TimeUtils.stringToDate(it) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupDismiss(viewLifecycleOwner, viewModel.updated)
    }

}