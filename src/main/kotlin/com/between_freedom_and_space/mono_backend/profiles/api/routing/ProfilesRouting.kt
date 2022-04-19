package com.between_freedom_and_space.mono_backend.profiles.api.routing

import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.profilesRouting() {
    val basePath = "/profile"

    routing {

        get("$basePath/all") {

        }

        get("$basePath/{id}") {

        }

        get("$basePath/{id}/s") {

        }

        get("$basePath/{id}/subscribers") {

        }
    }

    routing {

        post("$basePath/create") {

        }


        post("$basePath/exists") {

        }

        post("$basePath/subscribe/{id}") {

        }
    }

    routing {

        put("$basePath/update/{id}/text") {

        }

        put {

        }
    }

    routing {

        patch {

        }

        patch {

        }
    }

    routing {

        delete("$basePath/{id}") {

        }

        delete {

        }
    }
}