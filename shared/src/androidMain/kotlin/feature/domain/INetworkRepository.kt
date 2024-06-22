package feature.domain

import commons.domain.Callback
import entity.Friend

interface INetworkRepository {
    fun getContacts() : ArrayList<Friend>
    fun sendContactQuery(callback: Callback<List<Friend>>)
}