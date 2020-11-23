package cn.junmov.mirror.core.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import cn.junmov.mirror.databinding.FragmentListBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class PagedListFragment<T : Any> : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListBinding.inflate(inflater, container, false)
        val adapter = adapter()
        binding.listData.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            pagedData().collectLatest { adapter.submitData(it) }
        }
        setHasOptionsMenu(hasMenu())
        return binding.root
    }

    abstract fun adapter(): PagingDataAdapter<T, *>

    abstract fun pagedData(): Flow<PagingData<T>>

    open fun hasMenu(): Boolean = false

}