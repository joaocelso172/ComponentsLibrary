package feature.presenter

import entity.Friend

interface INetworkPresenter {
    fun fillNetworkComponent(listFriend: List<Friend>)
    fun showErrorScreen()
}