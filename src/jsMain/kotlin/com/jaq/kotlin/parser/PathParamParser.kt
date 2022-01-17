package com.jaq.kotlin.parser

import com.jaq.kotlin.param.Param
import com.jaq.kotlin.param.PathParam

class PathParamParser : Parser<Param> {
    private val _paramData = PathParam()

    override fun match(browserPath: String, routePath: String): Boolean {
        _paramData.cleanAll()
        val browserPaths = browserPath.split("/")
        val routePaths = routePath.split("/")
        if (browserPaths.size != routePaths.size) return false
        val pathMatchList = arrayListOf<Boolean>()
        browserPaths.forEachIndexed { pathIdx, pathValue ->
            if (pathIdx >= routePaths.size) return false
            val routeValue = routePaths[pathIdx]
            if (isPathVar(routeValue)) {
                _paramData.put(routeValue.removePrefix("{").removeSuffix("}"), pathValue)
                pathMatchList.add(true)
            } else if (pathValue == routeValue) {
                pathMatchList.add(true)
            } else {
                pathMatchList.add(false)
            }
        }
        return pathMatchList.reduce { acc, b -> acc && b };
    }

    override fun data(): Param {
        return _paramData
    }

    private fun isPathVar(currentPath: String) = currentPath.startsWith("{") && currentPath.endsWith("}")
}