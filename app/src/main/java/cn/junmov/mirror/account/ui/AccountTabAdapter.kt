package cn.junmov.mirror.account.ui

import androidx.fragment.app.Fragment
import cn.junmov.mirror.core.widget.AbstractTabAdapter

const val BALANCE_PAGE_INDEX = 0
const val CATEGORY_PAGE_INDEX = 1

class AccountTabAdapter(fragment: Fragment) : AbstractTabAdapter(fragment) {
    override val tabFragmentCreator: Map<Int, () -> Fragment> = mapOf(
        BALANCE_PAGE_INDEX to { BalanceAccountFragment() },
        CATEGORY_PAGE_INDEX to { CategoryAccountFragment() }
    )
}