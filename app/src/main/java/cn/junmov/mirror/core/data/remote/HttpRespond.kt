package cn.junmov.mirror.core.data.remote

class HttpRespond<T>(
    val status: Int = 0,
    val message: String,
    val data: T
) {

    fun isOk(): Boolean = status == 1

    override fun toString(): String =
        "HttpRespond[status=$status,message=$message,data=${data.toString()}]"
}
