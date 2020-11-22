package cn.junmov.mirror.core.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import cn.junmov.mirror.databinding.FragmentTabBinding
import com.google.android.material.tabs.TabLayoutMediator

abstract class AbstractTabFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTabBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabLayout
        val pager = binding.pager
        pager.adapter = pagerAdapter()
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = tabTitle(position)
        }.attach()
        setHasOptionsMenu(hasMenu())
        return binding.root
    }

    abstract fun pagerAdapter(): FragmentStateAdapter

    abstract fun tabTitle(position: Int): String?

    open fun hasMenu(): Boolean = false
}