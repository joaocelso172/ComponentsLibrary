package feature.presentation

import commons.Constants
import entity.Contacts

interface INetworkView {
    fun requestPermission()

    fun fillNetworkComponent(contactsList: List<Contacts>)

    fun showErrorScreen(errorMsg: String = Constants.DEFAULT_ERROR_MSG)

    fun showLoadingScreen()
}