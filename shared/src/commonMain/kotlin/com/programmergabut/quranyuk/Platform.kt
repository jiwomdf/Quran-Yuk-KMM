package com.programmergabut.quranyuk

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform