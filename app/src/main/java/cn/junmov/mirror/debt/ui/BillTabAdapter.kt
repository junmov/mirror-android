package cn.junmov.mirror.debt.ui

import androidx.fragment.app.Fragment
import cn.junmov.mirror.core.widget.AbstractTabAdapter

const val DEBT_PAGE_INDEX = 0
const val BILL_PAGE_INDEX = 1

class BillTabAdapter(fragment: Fragment) : AbstractTabAdapter(fragment) {
    override val tabFragmentCreator: Map<Int, () -> Fragment> = mapOf(
        DEBT_PAGE_INDEX to { DebtFragment() },
        BILL_PAGE_INDEX to { BillFragment() }
    )
}