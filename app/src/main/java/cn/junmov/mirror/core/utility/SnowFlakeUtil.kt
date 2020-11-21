package cn.junmov.mirror.core.utility

object SnowFlakeUtil {
    /** 时间部分所占长度  */
    private const val TIME_LEN = 41

    /** 数据中心id所占长度  */
    private const val DATA_LEN = 5

    /** 机器id所占长度  */
    private const val WORK_LEN = 5

    /** 毫秒内序列所占长度  */
    private const val SEQ_LEN = 12

    /** 定义起始时间 2015-01-01 00:00:00  */
    private const val START_TIME = 1420041600000L

    /** 上次生成ID的时间截  */
    private var LAST_TIME_STAMP = -1L

    /** 时间部分向左移动的位数 22  */
    private const val TIME_LEFT_BIT = 64 - 1 - TIME_LEN

    /** 自动获取数据中心id（可以手动定义 0-31之间的数）  */
    private const val DATA_ID = 10L

    /** 自动机器id（可以手动定义 0-31之间的数）  */
    private const val WORK_ID = 13L

    /** 数据中心id最大值 31  */
    private const val DATA_MAX_NUM = (-1 shl DATA_LEN).inv()

    /** 机器id最大值 31  */
    private const val WORK_MAX_NUM = (-1 shl WORK_LEN).inv()

    /** 随机获取数据中心id的参数 32  */
    private const val DATA_RANDOM = DATA_MAX_NUM + 1

    /** 随机获取机器id的参数 32  */
    private const val WORK_RANDOM = WORK_MAX_NUM + 1

    /** 数据中心id左移位数 17  */
    private const val DATA_LEFT_BIT = TIME_LEFT_BIT - DATA_LEN

    /** 机器id左移位数 12  */
    private const val WORK_LEFT_BIT = DATA_LEFT_BIT - WORK_LEN

    /** 上一次的毫秒内序列值  */
    private var LAST_SEQ = 0L

    /** 毫秒内序列的最大值 4095  */
    private const val SEQ_MAX_NUM = (-1 shl SEQ_LEN).inv().toLong()


    @Synchronized
    fun genId(): Long {
        var now = System.currentTimeMillis()
        if (now == LAST_TIME_STAMP) {
            LAST_SEQ = LAST_SEQ + 1 and SEQ_MAX_NUM
            if (LAST_SEQ == 0L) {
                now = nextMillis(
                    LAST_TIME_STAMP
                )
            }
        } else {
            LAST_SEQ = 0
        }

        //上次生成ID的时间截
        LAST_TIME_STAMP = now
        return (now - START_TIME) shl TIME_LEFT_BIT or (DATA_ID shl DATA_LEFT_BIT) or (WORK_ID shl WORK_LEFT_BIT) or LAST_SEQ
    }

    fun genIds(size: Int): List<Long> {
        val ids = mutableListOf<Long>()
        for (index in 0 until size) {
            ids.add(genId())
        }
        return ids
    }

    /**
     * 获取下一不同毫秒的时间戳，不能与最后的时间戳一样
     */
    private fun nextMillis(lastMillis: Long): Long {
        var now = System.currentTimeMillis()
        while (now <= lastMillis) {
            now = System.currentTimeMillis()
        }
        return now
    }

}