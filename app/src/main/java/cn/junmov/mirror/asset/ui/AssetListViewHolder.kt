package cn.junmov.mirror.asset.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.db.entity.Asset
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.TimeUtils

class AssetListViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    private val nameText: TextView = v.findViewById(R.id.list_item_asset_name)
    private val buildAtText: TextView = v.findViewById(R.id.list_item_asset_build_at)
    private val countText: TextView = v.findViewById(R.id.list_item_asset_count)
    private val sellSumText: TextView = v.findViewById(R.id.list_item_asset_sell_sum)
    private val buySumText: TextView = v.findViewById(R.id.list_item_asset_buy_sum)
    private val expenseText: TextView = v.findViewById(R.id.list_item_asset_expense)

    fun bindData(data: Asset) {
        buildAtText.text = TimeUtils.dateToString(data.buildAt)
        nameText.text = data.name
        countText.text = "${data.count}"
        buySumText.text = "买：${MoneyUtils.centToYuan(data.buySum)}"
        sellSumText.text = "卖：${MoneyUtils.centToYuan(data.sellSum)}"
        expenseText.text = "费：${MoneyUtils.centToYuan(data.expenseSum)}"
    }

    companion object {
        fun create(parent: ViewGroup): AssetListViewHolder {
            return AssetListViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item_asset, parent, false
                )
            )
        }
    }
}