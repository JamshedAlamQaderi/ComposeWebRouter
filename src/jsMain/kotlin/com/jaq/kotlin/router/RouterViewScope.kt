package com.jaq.kotlin.router

import androidx.compose.runtime.Composable
import com.jaq.kotlin.context.RouteContext
import com.jaq.kotlin.parser.Parser

class RouterViewScope(path: String, view: @Composable() (RouterViewScope.(RouteContext) -> Unit)?) : Route {
    private val _path = path
    private val _view = view

    @Composable
    fun renderView(routeContext: RouteContext) {
        _view?.let { it(routeContext) }
    }

    override fun isRenderer() = true

    override fun <T> render(browserUrl: String, parentUrl: String, parser: Parser<T>): RouteModel? {
        val routerUrl = parentUrl + _path
        if (parser.match(browserUrl, routerUrl)) {
            return RouteModel(routerUrl, _view)
        }
        return null
    }
}