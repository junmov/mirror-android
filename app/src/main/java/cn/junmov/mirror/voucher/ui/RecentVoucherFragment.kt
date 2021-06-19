package cn.junmov.mirror.voucher.ui

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.R
import cn.junmov.mirror.core.widget.AbstractListFragment
import cn.junmov.mirror.user.ui.ProfileViewModel
import cn.junmov.mirror.core.data.db.view.ItemVoucher
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentVoucherFragment : AbstractListFragment<ItemVoucher>() {

    private val profileViewModel: ProfileViewModel by activityViewModels()
    private val viewModel: RecentVoucherViewModel by viewModels()

    override fun whenAfterCreateView() {
        profileViewModel.isSigned.observe(viewLifecycleOwner) { isSigned ->
            if (!isSigned) {
                findNavController().navigate(R.id.screen_sign_in)
            }
        }
        profileViewModel.budgetMonth.observe(viewLifecycleOwner) {
            profileViewModel.checkBudgetMonth(it)
        }
    }

    override fun adapter(): ListAdapter<ItemVoucher, *> = VoucherListAdapter(
        onClickCopy = { viewModel.copy(it) },
        onClickRemove = { viewModel.remove(it) }
    )

    override fun data(): LiveData<List<ItemVoucher>> = viewModel.vouchers

    override fun hasMenu(): Boolean = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController()
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}