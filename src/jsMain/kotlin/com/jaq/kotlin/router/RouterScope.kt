package com.jaq.kotlin.router

import androidx.compose.runtime.Composable

class RouterScope(path: String) : Route {
    private var _path: String = path
    private val childRouters = arrayListOf<RouterScope>()
    private val childRoutes = arrayListOf<RouteModel>()

    fun router(path: String, child: RouterScope.() -> Unit) {
        val routerScope = RouterScope(path)
        routerScope.child()
        childRouters.add(routerScope)
    }

    fun route(path: String, view: @Composable () -> Unit) {
        childRoutes.add(RouteModel(path, view))
    }

    override fun render(browserUrl: String, parentUrl: String): RouteModel? {
        val routerUrl = (if (parentUrl == "") "/" else parentUrl) + _path
        val childRouter = childRouters.map { it.render(browserUrl, routerUrl) }.firstOrNull()
        if (childRouter !== null) {
            return childRouter
        }
        if (routerUrl == browserUrl) {
            val findChild = childRoutes.find {
                var childEmptyRoute = parentUrl + it.path
                childEmptyRoute = if (childEmptyRoute == "") "/" else childEmptyRoute
                childEmptyRoute == routerUrl
            }
            return findChild
        }
        val routes = childRoutes.filter {
            browserUrl.startsWith(this._path + it.path)
        }
        if (routes.isNotEmpty()) {
            return routes.firstOrNull()
        }
        return null
    }
}