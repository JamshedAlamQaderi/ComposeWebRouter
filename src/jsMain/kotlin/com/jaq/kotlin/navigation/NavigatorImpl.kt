package com.jaq.kotlin.navigation

import kotlinx.browser.document
import kotlinx.browser.window

class NavigatorImpl : Navigator {
    private val onNavigateCallbacks = arrayListOf<() -> Unit>()

    override fun onNavigate(callback: () -> Unit) {
        onNavigateCallbacks.add(callback)
    }

    override fun navigate(url: String, data: Any?) {
        window.history.pushState(null, "", url)
        onNavigateCallbacks.map { it() }
    }

    override fun goBack() {
        window.history.back()
    }

    override fun goForward() {
        window.history.forward()
    }

    override fun setTitle(title: String) {
        document.title = title
    }
}