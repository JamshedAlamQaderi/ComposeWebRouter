package com.jaq.kotlin.router

interface Route {
    fun render(browserUrl: String, parentUrl: String): RouteModel?
}