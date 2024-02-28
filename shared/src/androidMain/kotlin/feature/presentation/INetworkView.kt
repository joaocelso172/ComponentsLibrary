package feature.presentation

import commons.Constants
import entity.Friend

interface INetworkView {
    fun requestPermission()

    fun fillNetworkComponent(mutableListFriend: MutableList<Friend>)

    fun showErrorScreen(errorMsg: String = Constants.DEFAULT_ERROR_MSG)

    fun showLoadingScreen()
}