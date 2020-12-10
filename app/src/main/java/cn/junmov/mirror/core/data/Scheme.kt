package cn.junmov.mirror.core.data

object Scheme {

    const val DATABASE_NAME = "mirror_v2.db"
    const val DATABASE_VERSION = 2

    const val ID = "row_id"
    const val MODIFIED_AT = "modified_at"
    const val CREATE_AT = "create_at"
    const val DEL = "is_deleted"

    object Account {
        const val TABLE_NAME = "account"
        const val PARENT_ID = "parent_id"
        const val NAME = "name"
        const val FULL_NAME = "full_name"
        const val TYPE = "type"
        const val TRADE_COUNT = "trade_count"
        const val TRAD_ABLE = "trad_able"
        const val BASE = "base"
        const val INFLOW = "inflow"
        const val OUTFLOW = "outflow"
    }

    object Balance {
        const val TABLE_NAME = "balance"
        const val START_AT = "start_at"
        const val END_AT = "end_at"
        const val ACCOUNT_ID = "account_id"
        const val NAME = "name"
        const val BASE = "base"
        const val TRADE_COUNT = "trade_count"
        const val INFLOW = "outflow"
        const val OUTFLOW = "inflow"
    }

    object Voucher {
        const val TABLE_NAME = "voucher"
        const val SUMMARY = "summary"
        const val DATE_AT = "date_at"
        const val TIME_AT = "time_at"
        const val THING_ID = "thing_id"
        const val THING_NAME = "thing_name"
        const val PROFIT = "profit"
        const val IS_AUDITED = "is_audited"
        const val IS_TEMPLATE = "is_template"
    }

    object Split {
        const val TABLE_NAME = "split"
        const val VOUCHER_ID = "voucher_id"
        const val IS_DEBIT = "is_debit"
        const val AMOUNT = "amount"
        const val ACCOUNT_ID = "account_id"
        const val ACCOUNT_PARENT_ID = "account_parent_id"
        const val ACCOUNT_NAME = "account_name"
        const val ACCOUNT_TYPE = "account_type"
    }

    object Trade {
        const val TABLE_NAME = "trade"
        const val VOUCHER_ID = "voucher_id"
        const val ACCOUNT_ID = "account_id"
        const val ACCOUNT_TYPE = "account_type"
        const val AMOUNT = "amount"
        const val DATE_AT = "date_at"
    }

    object Thing {
        const val TABLE_NAME = "thing"
        const val NAME = "name"
    }

    object Debt {
        const val TABLE_NAME = "debt"
        const val SUMMARY = "summary"
        const val ACCOUNT_ID = "account_id"
        const val START_AT = "start_at"
        const val CAPITAL = "capital"
        const val COUNT = "count"
        const val INTEREST = "interest"
        const val COUNT_REPAY = "count_repay"
        const val CAPITAL_REPAY = "capital_repay"
        const val INTEREST_REPAY = "interest_repay"
        const val IS_SETTLED = "is_settled"
    }

    object Repay {
        const val TABLE_NAME = "repay"
        const val DEBT_ID = "debt_id"
        const val SUMMARY = "summary"
        const val INTEREST = "interest"
        const val DATE_AT = "date_at"
        const val CAPITAL = "capital"
        const val IS_SETTLED = "is_settled"
    }

    object Bill {
        const val TABLE_NAME = "bill"
        const val DATE_AT = "date_at"
        const val AMOUNT = "amount"
        const val IS_SETTLED = "is_settled"
    }

    object Asset {
        const val TABLE_NAME = "asset"
        const val NAME = "name"
        const val COUNT = "count"
        const val BUY = "buy"
        const val SELL = "sell"
    }

    object AssetLog {
        const val TABLE_NAME = "asset_log"
        const val ASSET_ID = "asset_id"
        const val IS_BUY = "is_buy"
        const val COUNT = "count"
        const val UNIT_PRICE = "unit_price"
        const val AMOUNT = "amount"
    }

    object ToDo {
        const val TABLE_NAME = "todo"
        const val SUMMARY = "summary"
        const val IS_ENABLED = "is_enabled"
        const val RUN_AT = "run_at"
        const val PERIOD = "period"
        const val DONE_TIMES = "done_times"
        const val DONE_TOTAL = "done_total"
    }
}