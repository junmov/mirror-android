package cn.junmov.mirror.mine.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import cn.junmov.mirror.sync.data.SyncRepository

class MineViewModel @ViewModelInject constructor(
    private val repository: SyncRepository
) : ViewModel() {

}