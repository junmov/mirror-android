package cn.junmov.mirror.asset.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cn.junmov.mirror.core.utility.navTo
import cn.junmov.mirror.databinding.FragmentAssetDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssetDetailFragment : Fragment() {

    private val viewModel: AssetDetailViewModel by viewModels()

    private val args: AssetDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAssetDetailBinding.inflate(inflater, container, false)
        val adapter = AssetLogListAdapter {
            findNavController().navigate(
                AssetDetailFragmentDirections.actionAssetDetailFragmentToAssetLogFormDialog(
                    args.assetId, it.id
                )
            )
        }
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@AssetDetailFragment
            listAssetLog.adapter = adapter
            btnAdjustAssetCount.navTo(
                AssetDetailFragmentDirections.actionAssetDetailFragmentToAssetLogFormDialog(args.assetId)
            )
        }
        viewModel.loadData(args.assetId)
        viewModel.assetLogs.observe(viewLifecycleOwner) { adapter.submitList(it) }
        return binding.root
    }

}