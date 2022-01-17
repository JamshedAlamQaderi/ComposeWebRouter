package com.jaq.kotlin.context

import com.jaq.kotlin.param.BaseParam
import com.jaq.kotlin.param.Param

interface Context {
    fun onNavigate(callback: () -> Unit)
    fun navigate(path: String, param: BaseParam? = null)
    fun back()
    fun forward()
    fun queryParams(): Param
    fun pathParams(): Param
    fun setTitle(title:String)
}