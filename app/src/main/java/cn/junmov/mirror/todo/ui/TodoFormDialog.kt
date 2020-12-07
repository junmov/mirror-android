package cn.junmov.mirror.todo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.PeriodType
import cn.junmov.mirror.core.utility.onClickItem
import cn.junmov.mirror.core.utility.setupDismiss
import cn.junmov.mirror.core.widget.FullScreenDialog
import cn.junmov.mirror.databinding.DialogTodoFormBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoFormDialog : FullScreenDialog() {

    private val viewModel: TodoFormViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DialogTodoFormBinding.inflate(inflater, container, false)
        val adapter = ArrayAdapter(
            requireContext(), R.layout.support_simple_spinner_dropdown_item,
            PeriodType.values()
        )
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@TodoFormDialog
            spinnerTodoPeriod.setAdapter(adapter)
            spinnerTodoPeriod.onClickItem { viewModel.selectPeriod(it as PeriodType) }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupDismiss(viewLifecycleOwner,viewModel.updated)
    }

}