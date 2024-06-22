package org.example.project.common.presentation.components

import entity.Friend


open class FriendListComponentState (
    val headerText: String = "",
    val friendList: ArrayList<Friend> = arrayListOf()
)

