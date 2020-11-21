package cn.junmov.mirror.account.ui

import android.view.View
import cn.junmov.mirror.account.data.AccountDiffCallBack
import cn.junmov.mirror.core.adapter.AbstractSingleLineListAdapter
import cn.junmov.mirror.core.adapter.OnNavListener
import cn.junmov.mirror.core.adapter.SingleLineModel
import cn.junmov.mirror.core.data.entity.Account

class ChildAccountListAdapter(
    private val navClick: OnNavListener
) : AbstractSingleLineListAdapter<Account>(AccountDiffCallBack) {

    override fun trans(data: Account): SingleLineModel.UiData = data.toSingleLineUiModel()

    override fun click(data: Account): View.OnClickListener? {
        return View.OnClickListener { navClick.click(data.id, data.fullName) }
    }

}