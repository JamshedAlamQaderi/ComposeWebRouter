package com.jaq.kotlin.dsl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.jaq.kotlin.navigation.NavigationFactory
import com.jaq.kotlin.router.RouterScope
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
    NavigationFactory.getNavigator().onNavigate {
        updateUi()
    }
    val routeModel = router.render(routerUrl.value, "")
    routeModel?.view?.let { it() }
}

fun renderComposableWithRouter(
    rootElementId: String,
    router: RouterScope.() -> Unit
) = renderComposable(rootElementId) {
    composeRouter(router)
}