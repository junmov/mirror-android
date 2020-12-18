package cn.junmov.mirror.core.data.entity

import java.time.LocalDateTime

interface CommonField {
    val id: Long
    val createAt: LocalDateTime
    var modifiedAt: LocalDateTime
    var deleted: Boolean
}