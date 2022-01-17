package com.jaq.kotlin

import com.jaq.kotlin.dsl.composeTestRouter
import com.jaq.kotlin.router.RouteModel
import org.jetbrains.compose.web.dom.Div
import kotlin.test.Test
import kotlin.test.assertEquals

class RouterTest {

    @Test
    fun visitToRouterTest() {
        val visitPath = "/api/v1/login"
        val routeModel = composeTestRouter(visitPath) {
            router("/api"){
                router("/v1"){
                    routeView("/login"){
                        println("from login page")
                        Div{}
                    }
                }
                routeView("/view1"){
                    println("from view 1")
                    Div {  }
                }
            }
        }
        assertEquals(visitPath, routeModel?.path)
    }

    @Test
    fun visitRouterTestWithPathVariable(){
        val visitPath = "/api/v1/Jamshed"
        val routeModel = composeTestRouter(visitPath) {
            router("/api"){
                router("/v1"){
                    routeView("/{name}"){
                        println("from path param parser page")
                        Div{}
                    }
                }
                routeView("/view1"){
                    println("from view 1")
                    Div {  }
                }
            }
        }
        assertEquals("/api/v1/{name}", routeModel?.path)
    }

}