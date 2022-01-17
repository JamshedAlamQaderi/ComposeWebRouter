package com.jaq.kotlin.context

import com.jaq.kotlin.param.BaseParam
import com.jaq.kotlin.param.Param
import com.jaq.kotlin.param.QueryParam
import com.jaq.kotlin.parser.PathParamParser
import kotlinx.browser.window

class RouteContext(pathParamParser: PathParamParser) : Context {
    private val _pathParamParser = pathParamParser

    override fun navigate(path: String, param: BaseParam) {
        window.history.pushState(null, "", param.build(path))
    }

    override fun back() {
        window.history.back()
    }

    override fun forward() {
        window.history.forward()
    }

    override fun queryParams(): Param {
        return QueryParam().parse(window.location.search)
    }

    override fun pathParams(): Param {
        return _pathParamParser.data()
    }
}