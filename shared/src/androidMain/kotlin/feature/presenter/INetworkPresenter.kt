package feature.presenter

import entity.Contacts

interface INetworkPresenter {
    fun fillNetworkComponent(listContacts: List<Contacts>)
    fun showErrorScreen()
}