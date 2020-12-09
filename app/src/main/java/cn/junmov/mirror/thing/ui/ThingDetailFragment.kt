package cn.junmov.mirror.thing.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import cn.junmov.mirror.MainNavDirections
import cn.junmov.mirror.R
import cn.junmov.mirror.core.adapter.TwoLineModel
import cn.junmov.mirror.core.adapter.TwoLinePagingAdapter
import cn.junmov.mirror.core.utility.setupSnackBar
import cn.junmov.mirror.core.utility.showInputDialog
import cn.junmov.mirror.core.widget.PagedListFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class ThingDetailFragment : PagedListFragment<TwoLineModel>() {

    private val args: ThingDetailFragmentArgs by navArgs()

    private val viewModel: ThingDetailViewModel by viewModels()

    override fun adapter(): PagingDataAdapter<TwoLineModel, *> = TwoLinePagingAdapter { id, _ ->
        findNavController().navigate(
            MainNavDirections.actionGlobalVoucherDetailFragment(id)
        )
    }

    override fun pagedData(): Flow<PagingData<TwoLineModel>> = viewModel.fetchData(args.thingId)

    override fun hasMenu(): Boolean = true

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
                showInputDialog("重命名项目", "重命名") {
                    viewModel.submitName(it, args.thingId, args.title)
                }
            }
            else -> false
        }
    }

}