package feature.presenter

import entity.Friend

interface INetworkPresenter {
    fun fillNetworkComponent(mutableListFriend: MutableList<Friend>)
    fun showErrorScreen()
}