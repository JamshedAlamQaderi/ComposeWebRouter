package com.jaq.kotlin

import com.jaq.kotlin.parser.PathParamParser
import kotlin.test.Test
import kotlin.test.assertEquals

class ParserTest {

    @Test
    fun pathParserMatchWithSameUrlTest() {
        val pathParser = PathParamParser()
        assertEquals(true, pathParser.match("/api/v1/users", "/api/v1/users"))
    }

    @Test
    fun pathParserMatchUrlWithPathVarTest() {
        val pathParser = PathParamParser()
        assertEquals(true, pathParser.match("/api/v1/Jamshed", "/api/v1/{name}"))
    }

    @Test
    fun pathParserMatchUrlWithPathVarDataTest() {
        val pathParser = PathParamParser()
        pathParser.match("/api/v1/Jamshed", "/api/v1/{name}")
        val data = pathParser.data()
        println("List of PathParams -> ${data.params}")
        assertEquals(arrayListOf(Pair("name", "Jamshed")), data.params)
    }
}