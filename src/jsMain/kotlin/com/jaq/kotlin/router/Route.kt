package com.jaq.kotlin.router

import com.jaq.kotlin.parser.Parser

@DslMarker
annotation class RouterDslMarker

@RouterDslMarker
interface Route {

    fun isRenderer(): Boolean
    fun <T> render(browserUrl: String, parentUrl: String, parser: Parser<T>): RouteModel?
}