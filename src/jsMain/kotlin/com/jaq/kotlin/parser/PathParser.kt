package com.jaq.kotlin.parser

class PathParser : Parser<List<Pair<String, String>>> {
    private val _data = arrayListOf<Pair<String, String>>()

    override fun match(browserPath: String, routePath: String, exactMatch: Boolean): Boolean {
        val browserPaths = browserPath.split("/")
        val routePaths = routePath.split("/")
        val pathVars = parsePathVariables(routePaths)
        browserPaths.forEachIndexed { urlIdx, urlValue ->
            if (urlIdx >= routePaths.size) {

            }
        }
        return false;
    }

    override fun data(): List<Pair<String, String>> {
        return _data
    }

    private fun parsePathVariables(routePaths: List<String>): List<Pair<Int, String>> {
        val varList = arrayListOf<Pair<Int, String>>()
        routePaths.forEachIndexed { index, value ->
            if (value.startsWith("{") && value.endsWith("}")) {
                varList.add(Pair(index, value))
            }
        }
        return varList
    }
}