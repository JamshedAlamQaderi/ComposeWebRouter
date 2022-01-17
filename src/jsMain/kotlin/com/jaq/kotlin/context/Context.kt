package com.jaq.kotlin.context

import com.jaq.kotlin.param.BaseParam
import com.jaq.kotlin.param.Param

interface Context {
    fun navigate(path: String, param: BaseParam?)
    fun back()
    fun forward()
    fun queryParams(): Param
    fun pathParams(): Param
}