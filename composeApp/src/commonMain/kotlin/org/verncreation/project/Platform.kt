package org.verncreation.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform