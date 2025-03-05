package com.feeltheboard.forgo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform