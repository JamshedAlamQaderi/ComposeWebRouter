package com.jaq.kotlin.param

import kotlinx.browser.window

class EmptyParam:BaseParam {
    override val params: ArrayList<Pair<String, String>> = arrayListOf()

    override fun parse(params: String): Param? {
        throw MethodNotWorkInEmptyParamException("parse")
    }

    override fun build(path: String): String? {
        return window.location.host + path
    }

    override fun put(key: String, value: String) {
        throw MethodNotWorkInEmptyParamException("put")
    }

    override fun get(key: String): String? {
        throw MethodNotWorkInEmptyParamException("get")
    }

    override fun getAll(key: String): Array<String?> {
        throw MethodNotWorkInEmptyParamException("getAll")
    }

    override fun cleanAll() {
        throw MethodNotWorkInEmptyParamException("cleanAll")
    }
}

class MethodNotWorkInEmptyParamException(methodName: String) :
    Exception("Method ('$methodName') not work on PathParam Class")