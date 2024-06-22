package org.example.project.common.presentation.components

import entity.Contacts


open class ContactsComponentRender (
    val headerText: String = "",
    val contactsList: ArrayList<Contacts> = arrayListOf()
)

