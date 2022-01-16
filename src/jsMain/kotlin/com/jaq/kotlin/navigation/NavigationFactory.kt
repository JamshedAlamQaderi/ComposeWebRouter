package com.jaq.kotlin.navigation

object NavigationFactory {
    private val navigator: Navigator

    init {
        navigator = NavigatorImpl()
    }

    fun getNavigator(): Navigator {
        return navigator
    }
}

val navigator = NavigationFactory.getNavigator()