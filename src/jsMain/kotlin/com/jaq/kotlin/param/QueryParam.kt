package com.jaq.kotlin.param

import kotlinx.browser.window
import org.w3c.dom.url.URL
import org.w3c.dom.url.URLSearchParams

class QueryParam : BaseParam {
    override val params: ArrayList<Pair<String, String>> =  arrayListOf()

    override fun put(key: String, value: String) {
        params.add(Pair(key, value))
    }

    override fun get(key: String): String? {
        return params.firstOrNull { it.first == key }?.second
    }

    override fun getAll(key: String): Array<String?> {
        return params.filter { it.first == key }.map { it.second }.toTypedArray()
    }

    override fun parse(params: String): Param {
        val searchParams = URLSearchParams(params)
        val keys = searchParams.keys()
        keys.toSet().forEach { key ->
            searchParams.getAll(key).forEach { value ->
                put(key, value)
            }
        }
        return this
    }

    override fun build(path: String): String {
        val fullUrl = window.location.host + path
        val _url = URL(fullUrl)
        params.forEach {
            _url.searchParams.append(it.first, it.second)
        }
        return _url.toString()
    }

    override fun cleanAll() {
        params.clear()
    }

}

fun URLSearchParams.keys(): List<String> {
    val keyList = mutableListOf<String>()
    val _keys = asDynamic().keys()
    var value = _keys.next()
    if (value.value != null) {
        keyList.add(value.value)
    }
    while (!value.done) {
        value = _keys.next()
        if (value.value != null) {
            keyList.add(value.value)
        }
    }
    return keyList
}