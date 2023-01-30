package com.addressbook.requests

import com.example.addressbookdb.GroupId
import com.example.addressbookdb.PersonId


data class AddGroupRequest (
    val groupName: String,
)

data class UpdateGroupRequest(
    val groupId: GroupId,
    val groupName: String,
)