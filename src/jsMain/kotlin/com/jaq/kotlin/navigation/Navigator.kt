package com.jaq.kotlin.navigation

interface Navigator {
    fun onNavigate(callback: () -> Unit)
    fun navigate(url: String, data: Any? = null)
    fun goBack()
    fun goForward()
    fun setTitle(title: String)
}