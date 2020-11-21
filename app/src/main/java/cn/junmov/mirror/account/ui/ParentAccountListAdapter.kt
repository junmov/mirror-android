package cn.junmov.mirror.account.ui

import android.view.View
import cn.junmov.mirror.account.data.AccountDiffCallBack
import cn.junmov.mirror.core.adapter.AbstractTwoLineListAdapter
import cn.junmov.mirror.core.adapter.OnNavListener
import cn.junmov.mirror.core.adapter.TwoLineModel
import cn.junmov.mirror.core.data.entity.Account

class ParentAccountListAdapter(
    private val navClick: OnNavListener
) : AbstractTwoLineListAdapter<Account>(AccountDiffCallBack) {

    override fun trans(data: Account): TwoLineModel.UiData {
        return data.toTwoLineUiModel()
    }

    override fun click(data: Account): View.OnClickListener? {
        return View.OnClickListener { navClick.click(data.id, data.name) }
    }

}