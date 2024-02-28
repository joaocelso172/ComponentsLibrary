package feature.domain

import commons.domain.Callback
import entity.Friend

interface INetworkRepository {
    fun sendContactQuery(callback: Callback<MutableList<Friend>>)
}