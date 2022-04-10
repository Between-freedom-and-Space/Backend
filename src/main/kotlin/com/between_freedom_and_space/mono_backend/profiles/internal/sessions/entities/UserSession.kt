package com.between_freedom_and_space.mono_backend.profiles.internal.sessions.entities

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserSession(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<UserSession>(UserSessionsTable)

    var location by UserSessionsTable.location
}