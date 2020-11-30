package cn.junmov.mirror.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.adapter.SingleLineAble
import cn.junmov.mirror.core.adapter.SingleLineModel
import cn.junmov.mirror.core.data.AccountType
import cn.junmov.mirror.core.data.Scheme
import cn.junmov.mirror.core.utility.MoneyUtils
import java.time.LocalDateTime

@Entity(
    tableName = Scheme.Split.TABLE_NAME,
    indices = [
        Index(Scheme.Split.VOUCHER_ID),
        Index(Scheme.Split.ACCOUNT_ID)
    ]
)
data class Split(
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.Split.VOUCHER_ID) override val voucherId: Long,
    @ColumnInfo(name = Scheme.Split.AMOUNT) override var amount: Int,
    @ColumnInfo(name = Scheme.Split.IS_DEBIT) override var isDebit: Boolean,
    @ColumnInfo(name = Scheme.Split.ACCOUNT_ID) override var accountId: Long,
    @ColumnInfo(name = Scheme.Split.ACCOUNT_PARENT_ID) override var accountParentId: Long,
    @ColumnInfo(name = Scheme.Split.ACCOUNT_NAME) override var accountName: String,
    @ColumnInfo(name = Scheme.Split.ACCOUNT_TYPE) override var accountType: AccountType,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.DEL) override var isDeleted: Boolean = false,
) : SplitEntity, SingleLineAble {

    override fun toSingleLineUiModel(): SingleLineModel.UiData = SingleLineModel.UiData(
        id = id, primary = if (isDebit) "借: $accountName" else "贷: $accountName",
        action = MoneyUtils.centToYuan(amount), separator = "", title = ""
    )

}
