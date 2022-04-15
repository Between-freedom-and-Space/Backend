package com.between_freedom_and_space.mono_backend.profiles.entities

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UserProfilesTable: LongIdTable("user_profiles") {

    val mail = text("mail")

    val passwordEncrypted = text("password_encrypted", eagerLoading = true)

    val nickName = text("nick_name", eagerLoading = true)

    val nameAlias = text("name_alias", eagerLoading = true)

    val description = text("description", eagerLoading = true).nullable()

    val location = text("location", eagerLoading = true).nullable()

    val isDeleted = bool("is_deleted").default(false)

    val isVisible = bool("is_visible").default(true)

    val createdDate = datetime("created_date")

    val updatedDate = datetime("updated_date")
}