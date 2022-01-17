package com.jaq.kotlin.router

import androidx.compose.runtime.Composable
import com.jaq.kotlin.context.Context
import com.jaq.kotlin.context.RouteContext
import com.jaq.kotlin.parser.Parser

class RouterViewScope(path: String, view: @Composable() (RouterViewScope.(Context) -> Unit)?) : Route {
    private val _path = path
    private val _view = view

    @Composable
    fun renderView(context: Context) {
        _view?.let { it(context) }
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