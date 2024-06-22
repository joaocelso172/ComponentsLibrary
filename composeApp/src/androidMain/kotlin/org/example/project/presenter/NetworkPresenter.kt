package org.example.project.presenter

import entity.Contacts
import feature.presentation.INetworkView
import feature.presenter.INetworkPresenter

class NetworkPresenter(private val view: INetworkView) : INetworkPresenter {


    override fun fillNetworkComponent(listContacts: List<Contacts>) {
        view.fillNetworkComponent(listContacts)
    }

    override fun showErrorScreen() {
        view.showErrorScreen("Error")
    }

    fun showLoadingScreen(){
        view.showLoadingScreen()
    }


}