package com.jaq.kotlin.dsl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.jaq.kotlin.context.RouteContext
import com.jaq.kotlin.parser.PathParamParser
import com.jaq.kotlin.router.RouteModel
import com.jaq.kotlin.router.RouterScope
import com.jaq.kotlin.router.RouterViewScope
import kotlinx.browser.window
import org.jetbrains.compose.web.renderComposable

@Composable
fun composeRouter(childRouter: RouterScope.() -> Unit) {
    val router = RouterScope("").apply(childRouter)
    val routerUrl = remember { mutableStateOf(window.location.pathname) }
    val updateUi: () -> Unit = {
        routerUrl.value = window.location.pathname
    }
    window.addEventListener("DOMContentLoaded", {
        updateUi()
    })
    window.addEventListener("popstate", {
        updateUi()
    })

    val pathParamParser = PathParamParser()
    val routeContext = RouteContext(pathParamParser)
    routeContext.onNavigate {
        updateUi()
    }
    val routeModel = router.render(routerUrl.value, "", pathParamParser)
    val routerViewScope = RouterViewScope(routeModel?.path ?: "", routeModel?.view)
    routerViewScope.renderView(routeContext)
}

fun renderComposableWithRouter(
    rootElementId: String,
    router: RouterScope.() -> Unit
) = renderComposable(rootElementId) {
    composeRouter(router)
}

fun composeTestRouter(path: String, childRouter: RouterScope.() -> Unit): RouteModel? {
    val router = RouterScope("").apply(childRouter)
    val pathParamParser = PathParamParser()
    return router.render(path, "", pathParamParser)
}