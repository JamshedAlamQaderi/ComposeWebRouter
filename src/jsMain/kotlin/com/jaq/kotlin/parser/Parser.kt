package com.jaq.kotlin.parser

interface Parser<T> {
    fun match(browserPath: String, routePath: String, exactMatch: Boolean = false):Boolean
    fun data():T?
}