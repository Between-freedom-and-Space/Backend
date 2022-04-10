package com.between_freedom_and_space.mono_backend.app

import com.between_freedom_and_space.mono_backend.access.plugin.addAccessPlugin
import com.between_freedom_and_space.mono_backend.auth.plugins.addAuthPlugin
import com.between_freedom_and_space.mono_backend.plugins.configureRouting
import com.between_freedom_and_space.mono_backend.posts.plugins.addPostsPlugin
import com.between_freedom_and_space.mono_backend.profiles.plugins.addProfilePlugin
import com.between_freedom_and_space.mono_backend.recommendations.plugin.addRecommendationsPlugin
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.metrics.micrometer.*
import io.ktor.server.plugins.*
import io.netty.handler.codec.http2.DefaultHttp2Headers

fun Application.main() {
    // Plugins
//    install(CallLogging)
//    install(Authentication)
//    install(CORS)
//    install(MicrometerMetrics)

    // Test
    configureRouting()

    // Components
    addAccessPlugin()
    addAuthPlugin()
    addPostsPlugin()
    addProfilePlugin()
    addRecommendationsPlugin()
}