package cn.junmov.mirror.core.data

object Scheme {

    const val DATABASE_NAME = "mirror.db"
    const val DATABASE_VERSION = 1

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
        const val BALANCE = "balance"
        const val SORT_KEY = "sort_key"
        const val IS_LEAF = "is_leaf"
    }

    object Voucher {
        const val TABLE_NAME = "voucher"
        const val SUMMARY = "summary"
        const val DATE_AT = "date_at"
        const val TIME_AT = "time_at"
        const val THING_ID = "thing_id"
        const val THING_NAME = "thing_name"
        const val PROFIT = "profit"
    }

    object Split {
        const val TABLE_NAME = "split"
        const val VOUCHER_ID = "voucher_id"
        const val IS_DEBIT = "is_debit"
        const val AMOUNT = "amount"
        const val ACCOUNT_ID = "account_id"
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

    object Budget {
        const val TABLE_NAME = "budget"
        const val MONTH_AT = "month_at"
        const val PARENT_ID = "parent_id"
        const val ACCOUNT_ID = "account_id"
        const val ACCOUNT_NAME = "account_name"
        const val TOTAL = "total"
        const val USED = "used"
        const val DELTA = "delta"
    }
}