package cn.junmov.mirror.core.widget

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

abstract class AbstractTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    protected abstract val tabFragmentCreator: Map<Int, () -> Fragment>

    override fun getItemCount(): Int = tabFragmentCreator.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentCreator[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

}