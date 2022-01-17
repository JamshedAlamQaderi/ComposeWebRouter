package com.jaq.kotlin

import com.jaq.kotlin.param.QueryParam
import com.jaq.kotlin.param.keys
import kotlinx.browser.window
import org.w3c.dom.url.URL
import org.w3c.dom.url.URLSearchParams
import kotlin.test.Test
import kotlin.test.assertEquals

class ParamTest {

    @Test
    fun buildUrlUsingQueryParam() {
        val queryParam = QueryParam()
        queryParam.put("name", "Jamshed Alam")
        val path = "/api/search_user/"
        val url = queryParam.build(path)
        assertEquals(url, "$path?name=Jamshed+Alam")
    }

    @Test
    fun parseQueryParamFromUrlSearch() {
        val queryUrl = URL(window.location.host + "/api").apply {
            searchParams.apply {
                append("size", "l")
                append("size", "m")
                append("name", "Jamshed")
            }
        }
        val queryParamParsed = QueryParam()
        queryParamParsed.parse(queryUrl.search.removePrefix("?"))
        val parserParams = queryParamParsed.build("/api")
        val queryParamBuild = QueryParam().apply {
            put("size", "l")
            put("size", "m")
            put("name", "Jamshed")
        }.build("/api")

        assertEquals(parserParams, queryParamBuild)
    }
}