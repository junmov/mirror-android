package cn.junmov.mirror.budget.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.AccountType
import cn.junmov.mirror.core.data.entity.Account
import cn.junmov.mirror.core.utility.onClickItem
import cn.junmov.mirror.core.utility.setupDismiss
import cn.junmov.mirror.core.widget.FullScreenDialog
import cn.junmov.mirror.databinding.DialogCategoryFormBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFormDialog : FullScreenDialog() {

    private val viewModel: CategoryFormViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DialogCategoryFormBinding.inflate(inflater, container, false)
        val typeAdapter = ArrayAdapter(
            requireContext(), R.layout.support_simple_spinner_dropdown_item,
            AccountType.categorys
        )
        val accountAdapter = ArrayAdapter<Account>(
            requireContext(), R.layout.support_simple_spinner_dropdown_item,
        )
        binding.apply {
            vm = viewModel
            spinnerCategoryParent.setAdapter(accountAdapter)
            spinnerCategoryParent.onClickItem { viewModel.selectParent(it as Account) }
            spinnerCategoryType.setAdapter(typeAdapter)
            spinnerCategoryType.onClickItem { viewModel.selectType(it as AccountType) }
            lifecycleOwner = this@CategoryFormDialog
        }
        viewModel.firstCategory.observe(viewLifecycleOwner) { accountAdapter.addAll(it) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupDismiss(viewLifecycleOwner, viewModel.updated)
    }

}