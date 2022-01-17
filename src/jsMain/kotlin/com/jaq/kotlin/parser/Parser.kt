package com.jaq.kotlin.parser

interface Parser<T> {
    fun match(browserPath: String, routePath: String):Boolean
    fun data():T?
}