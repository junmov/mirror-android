package cn.junmov.mirror.asset.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.db.entity.AssetLog
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.TimeUtils

class AssetLogViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    private val assetLogType: TextView = v.findViewById(R.id.asset_log_type)
    private val assetLogName: TextView = v.findViewById(R.id.asset_log_name)
    private val assetLogDateAt: TextView = v.findViewById(R.id.asset_log_date)
    private val assetLogCount: TextView = v.findViewById(R.id.asset_log_count)
    private val assetLogPrice: TextView = v.findViewById(R.id.asset_log_price)
    private val assetLogExpense: TextView = v.findViewById(R.id.asset_log_expense)
    private val assetLogAmount: TextView = v.findViewById(R.id.asset_log_amount)
    private val assetLogReason: TextView = v.findViewById(R.id.asset_log_reason)

    fun bindData(data: AssetLog) {
        assetLogType.text = if (data.buy) "买入" else "卖出"
        assetLogAmount.text = if (data.success) MoneyUtils.centToYuan(data.amount) else "—"
        assetLogName.text = data.assetName
        assetLogExpense.text =
            if (data.success) "费用：${MoneyUtils.centToYuan(data.expense)}" else "—"
        assetLogCount.text = "数量：${data.count}"
        assetLogDateAt.text = TimeUtils.dateToString(data.dateAt)
        assetLogPrice.text = "单价：${data.price}"
        assetLogReason.text = data.reason
    }

    companion object {
        fun create(parent: ViewGroup): AssetLogViewHolder {
            return AssetLogViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item_asset_log, parent, false
                )
            )
        }
    }

}