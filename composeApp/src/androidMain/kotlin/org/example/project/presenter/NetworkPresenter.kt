package org.example.project.presenter

import entity.Friend
import feature.presentation.INetworkView
import feature.presenter.INetworkPresenter

class NetworkPresenter(private val view: INetworkView) : INetworkPresenter {


    override fun fillNetworkComponent(mutableListFriend: MutableList<Friend>) {
        view.fillNetworkComponent(mutableListFriend)
    }

    override fun showErrorScreen() {
        view.showErrorScreen("Error")
    }

    fun showLoadingScreen(){
        view.showLoadingScreen()
    }


}