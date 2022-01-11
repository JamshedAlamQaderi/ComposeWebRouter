package com.jaq.kotlin.router

import androidx.compose.runtime.Composable

data class RouteModel(val path: String, val view: @Composable () -> Unit)