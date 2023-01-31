package com.addressbook.requests

import com.addressbook.GroupId

data class AddGroupRequest (
    val groupName: String,
)

data class UpdateGroupRequest(
    val groupId: GroupId,
    val groupName: String,
)