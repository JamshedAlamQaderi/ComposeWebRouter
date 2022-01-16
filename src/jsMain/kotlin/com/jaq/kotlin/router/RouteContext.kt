package com.jaq.kotlin.router

import com.jaq.kotlin.encodeURI
import com.jaq.kotlin.param.Param
import kotlinx.browser.window

class RouteContext {

    fun navigate(url:String, param:Param){
        val encodedUrl = encodeURI(url)
    }

    fun back(){

    }

    fun forward(){

    }
}