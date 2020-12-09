package cn.junmov.mirror.home.ui

import androidx.fragment.app.Fragment
import cn.junmov.mirror.core.widget.AbstractTabAdapter

const val HOME_TRADE_PAGE_INDEX = 0
const val HOME_TODO_PAGE_INDEX = 1

class HomeTabAdapter(fragment: Fragment) : AbstractTabAdapter(fragment) {
    override val tabFragmentCreator: Map<Int, () -> Fragment> = mapOf(
        HOME_TODO_PAGE_INDEX to { HomeToDoFragment() },
        HOME_TRADE_PAGE_INDEX to { HomeTradeFragment() }
    )
}