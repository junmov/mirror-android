package cn.junmov.mirror.thing.ui

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cn.junmov.mirror.MainNavDirections
import cn.junmov.mirror.R
import cn.junmov.mirror.core.adapter.TwoLinePagingAdapter
import cn.junmov.mirror.core.utility.setupSnackBar
import cn.junmov.mirror.databinding.FragmentThingDetailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ThingDetailFragment : Fragment() {

    private val args: ThingDetailFragmentArgs by navArgs()

    private val viewModel: ThingDetailViewModel by viewModels()

    private val adapter = TwoLinePagingAdapter { id, _ ->
        findNavController().navigate(
            MainNavDirections.actionGlobalVoucherDetailFragment(id)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentThingDetailBinding.inflate(inflater, container, false)
        binding.listThingVoucher.adapter = adapter
        fetchData(args.thingId)
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun fetchData(id: Long) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchData(id).collectLatest { adapter.submitData(it) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setupSnackBar(viewLifecycleOwner, viewModel.message, Snackbar.LENGTH_SHORT)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_edit, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_edit -> {
                showRenameDialog()
                true
            }
            else -> false
        }
    }

    private fun showRenameDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("重命名项目")
            .setView(R.layout.dialog_single_edit)
            .setPositiveButton("重命名") { dialog, _ ->
                val text = (dialog as AlertDialog).findViewById<TextView>(R.id.dialog_single_text)
                if (text != null) {
                    viewModel.rename(text.text.toString(), args.thingId, args.title)
                }
            }.setNegativeButton("取消", null)
            .show()
    }

}