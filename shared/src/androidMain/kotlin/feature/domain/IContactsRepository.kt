package feature.domain

import commons.domain.Callback
import entity.Contacts

interface IContactsRepository {
    fun getContacts() : ArrayList<Contacts>
    fun sendContactQuery(callback: Callback<List<Contacts>>)
}