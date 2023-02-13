package com.addressbook.requests

import com.addressbook.models.GroupId
import java.util.*

data class AddGroupRequest (
    val groupName: String,
)

data class UpdateGroupRequest(
    val groupId: UUID,
    val groupName: String,
)