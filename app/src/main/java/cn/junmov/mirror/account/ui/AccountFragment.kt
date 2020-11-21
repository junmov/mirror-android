package cn.junmov.mirror.account.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import cn.junmov.mirror.R
import cn.junmov.mirror.databinding.FragmentAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private val viewModel: AccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAccountBinding.inflate(inflater, container, false)
        val adapter = ParentAccountListAdapter { id, title ->
            findNavController().navigate(
                AccountFragmentDirections.actionPageAccountToAccountDetailFragment(id, title)
            )
        }
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@AccountFragment
            listAccounts.adapter = adapter
        }
        viewModel.loadData()
        viewModel.accounts.observe(viewLifecycleOwner) { adapter.submitList(it) }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_create, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_create -> {
                findNavController().navigate(
                    AccountFragmentDirections.actionPageAccountToAccountFormDialog()
                )
                true
            }
            else -> false
        }
    }

}