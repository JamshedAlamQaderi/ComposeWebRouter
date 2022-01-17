package com.jaq.kotlin.context

import com.jaq.kotlin.param.BaseParam
import com.jaq.kotlin.param.Param
import com.jaq.kotlin.param.QueryParam
import com.jaq.kotlin.parser.PathParamParser
import kotlinx.browser.document
import kotlinx.browser.window

class RouteContext(pathParamParser: PathParamParser) : Context {
    private val _pathParamParser = pathParamParser
    private val _onNavigateCallbacks = arrayListOf<() -> Unit>()

    override fun onNavigate(callback: () -> Unit) {
        _onNavigateCallbacks.add(callback)
    }

    override fun setTitle(title: String) {
        document.title = title
    }

    override fun navigate(path: String, param: BaseParam?) {
        val _param = if (param == null) path else param.build(path)
        window.history.pushState(null, "", _param)
        _onNavigateCallbacks.forEach { it() }
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