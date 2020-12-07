package cn.junmov.mirror.core.data.entity

interface AssetEntity : CommonField {

    /** 资产名称 */
    var name: String

    /** 持有数量 */
    var count: Int

    /** 已投入成本 */
    var buy: Int

    /** 已收到回报 */
    var sell: Int

}