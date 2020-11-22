package cn.junmov.mirror.core.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.databinding.FragmentListBinding

abstract class AbstractListFragment<T> : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListBinding.inflate(inflater, container, false)
        val adapter = adapter()
        binding.listData.adapter = adapter
        data().observe(viewLifecycleOwner) { adapter.submitList(it) }
        setHasOptionsMenu(hasMenu())
        return binding.root
    }

    open fun hasMenu(): Boolean = false

    abstract fun adapter(): ListAdapter<T, *>

    abstract fun data(): LiveData<List<T>>

}