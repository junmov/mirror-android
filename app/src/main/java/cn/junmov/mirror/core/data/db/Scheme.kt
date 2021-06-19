package cn.junmov.mirror.core.data.db

object Scheme {

    const val DATABASE_NAME = "mirror.db"
    const val DATABASE_VERSION = 8

    const val ID = "row_id"
    const val MODIFIED_AT = "modified_at"
    const val CREATE_AT = "create_at"
    const val DEL = "is_deleted"

    object Account {
        const val TABLE_NAME = "account"
        const val NAME = "name"
        const val TYPE = "type"
        const val BALANCE = "base"
        const val BUDGET = "budget"
        const val DEBIT = "debit"
        const val CREDIT = "credit"
        const val TRADE_COUNT = "trade_count"
    }

    object Voucher {
        const val TABLE_NAME = "voucher"
        const val VIEW_NAME = "item_voucher"
        const val SUMMARY = "summary"
        const val DATE_AT = "date_at"
        const val TIME_AT = "time_at"
        const val THING = "thing"
        const val AMOUNT = "amount"
        const val DEBIT_ID = "debit_id"
        const val DEBIT_NAME = "debit_name"
        const val CREDIT_ID = "credit_id"
        const val CREDIT_NAME = "credit_name"
    }

    object Debt {
        const val TABLE_NAME = "debt"
        const val CAPITAL = "capital"
        const val CAPITAL_REPAY = "capital_repay"
        const val INTEREST_REPAY = "interest_repay"
        const val IS_REPAID = "is_repaid"
        const val BORROW_AT = "borrow_at"
        const val BORROW_ID = "borrow_id"
        const val BORROW_NAME = "borrow_name"
        const val REMARK = "remark"
    }

    object Repay {
        const val TABLE_NAME = "repay"
        const val DEBT_ID = "debt_id"
        const val INTEREST = "interest"
        const val DATE_AT = "date_at"
        const val CAPITAL = "capital"
        const val IS_SETTLED = "is_settled"
    }

    object Asset {
        const val TABLE_NAME = "asset"
        const val NAME = "name"
        const val BUILD_AT = "build_at"
        const val COUNT = "count"
        const val EXPENSE = "expense"
        const val BUY = "buy_sum"
        const val SELL = "sell_sum"
        const val PROFIT = "profit"
        const val IS_ACTIVE = "is_active"
    }

    object AssetLog {
        const val TABLE_NAME = "asset_log"
        const val ASSET_ID = "asset_id"
        const val ASSET_NAME = "asset_name"
        const val DATE_AT = "date_at"
        const val IS_BUY = "is_buy"
        const val COUNT = "count"
        const val PRICE = "price"
        const val EXPENSE = "expense"
        const val AMOUNT = "amount"
        const val REASON = "reason"
        const val SUCCESS = "is_success"
    }

}