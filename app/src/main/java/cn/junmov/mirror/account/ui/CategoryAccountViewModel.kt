package cn.junmov.mirror.account.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import cn.junmov.mirror.account.domain.FlowAllCategoryAccountUseCase

class CategoryAccountViewModel @ViewModelInject constructor(
    private val flowAllCategoryAccount: FlowAllCategoryAccountUseCase
) : ViewModel() {

    val accounts = flowAllCategoryAccount().asLiveData()

}