package cn.junmov.mirror.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.adapter.SingleLineAble
import cn.junmov.mirror.core.adapter.SingleLineModel
import cn.junmov.mirror.core.adapter.TwoLineAble
import cn.junmov.mirror.core.adapter.TwoLineModel
import cn.junmov.mirror.core.data.AccountType
import cn.junmov.mirror.core.data.Scheme
import cn.junmov.mirror.core.utility.MoneyUtils
import java.time.LocalDateTime

@Entity(
    tableName = Scheme.Account.TABLE_NAME,
    indices = [
        Index(value = [Scheme.Account.NAME], unique = true),
        Index(value = [Scheme.Account.PARENT_ID])
    ]
)
data class Account(
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.Account.NAME) override var name: String,
    @ColumnInfo(name = Scheme.Account.FULL_NAME) override var fullName: String,
    @ColumnInfo(name = Scheme.Account.TYPE) override val type: AccountType,
    @ColumnInfo(name = Scheme.Account.SORT_KEY) override var sortKey: Int,
    @ColumnInfo(name = Scheme.Account.PARENT_ID) override var parentId: Long,
    @ColumnInfo(name = Scheme.Account.IS_LEAF) override val isLeaf: Boolean,
    @ColumnInfo(name = Scheme.Account.BALANCE) override var balance: Int,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.DEL) override var isDeleted: Boolean = false
) : AccountEntity, SingleLineAble, TwoLineAble {

    fun isSonOf(parent: Account): Boolean = parentId == parent.id

    override fun toTwoLineUiModel(): TwoLineModel.UiData = TwoLineModel.UiData(
        id = id, primary = name, secondary = type.toString(),
        action = MoneyUtils.centToYuan(balance), title = name, separator = ""
    )

    override fun toSingleLineUiModel(): SingleLineModel.UiData = SingleLineModel.UiData(
        id = id, primary = name, action = MoneyUtils.centToYuan(balance), title = name,
        separator = ""
    )


    override fun toString(): String = fullName

}